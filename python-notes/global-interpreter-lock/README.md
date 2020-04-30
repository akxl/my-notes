A short note on the CPython Global Interpreter Lock  (GIL)
==========================================================

This is an _extremely_ simplified short note on how the GIL works, and any near-enough Java equivalents to draw parallels (pun not intended).

Prelude - what's the difference between Python and CPython?
-----------------------------------------------------------

A Java version of this question would be "What's the difference between Java and the JVM?". The Java _language_ is the language that you write your logic in. It includes things like `if...else`, `public static void`, the other keywords, and the variable declarations etc. The Java Virtual Machine (JVM), in very simple terms, is the thing that actually takes the logic that you've written and _runs_ it.

It's because there's this constructed decoupling between the language and the thing that runs the language, you can very well change either part and your logic or algorithm will still work. That's why there are many flavours of the JVM, e.g. Oracle, OpenJDK, Amazon Corretto, GraalVM etc. You can pick any one of these JVMs to run your code. On the language end, you don't have to use Java to write code that runs on a JVM either. That's why you have languages such as Scala, Kotlin, Groovy, Clojure etc. As long as the language can be compiled down to Java bytecode, it can run on any JVM; conversely as long as your app can run Java bytecode, it is a JVM.

Back to Python - Python refers to the language. CPython is the JVM counterpart. It's just one of the many implementation, just like Oracle is just one of the many JVM implementations. It just so happens that CPython is the most popular one so far. When you download python from `python.org` today, you will get CPython.

CPython and the GIL
-------------------

So what does CPython have to do with the Global Interpreter Lock (GIL)? Better yet, what is the GIL anyway?

Okay, so you know how in Java, when we are writing concurrent code, we use locks to make sure that only one thread accesses a particular protected object at a time? This is what the GIL is too - it's a lock that is used when Python is asked to run concurrent code. The key difference, however, is that in CPython, you can only run _one thread at a time_. Yeap. One thread at a time. (For those of us who are well versed with how javascript is typically executed today, you won't be as surprised. But for the rest of us, this can be quite a shock.) To _ensure_ that only one thread runs at any one time, that's why the _Global_ Interpreter Lock exists.

Why, you might ask, does CPython only run one thread at a time? Well, you only have to go onto the official documentation page at https://wiki.python.org/moin/GlobalInterpreterLock to see that "CPython's memory management is not thread-safe". There you go. That's the answer.

However, the same page does list a couple of examples of Python implementations that do not have this limitation: Jython and IronPython.

Okay, so how is this GIL implemented?
-------------------------------------

NOTE: C code ahead. The source code of CPython can be found here: https://github.com/python/cpython

The source code for CPython is fairly well documented, so I'm going to lift this particular section found in `Python/ceval_gil.h`:

> The GIL is just a boolean variable (locked) whose access is protected by a mutex (gil_mutex)

If you look into `Include/internal/pycore_gil.h`, you'll see this `struct`:
```c
struct _gil_runtime_state {
    unsigned long interval;
    _Py_atomic_address last_holder;
    
    /* Whether the GIL is already taken (-1 if uninitialized). This is
       atomic because it can be read without any lock taken in ceval.c. */
    _Py_atomic_int locked;
    

    unsigned long switch_number;
    PyCOND_T cond;
    PyMUTEX_T mutex;
};
```
The actual lock is the `_Py_atomic_int` variable named `locked`, which is basically an integer that flags if the lock is held or not. That's really it really. This lock is protected by a `PyMUTEX_T`. This mutex is actually OS-dependent. If you have the Linux version, you'll see in `Include/internal/pycore_condvar.h` that this gets translated by a macro to be a `pthread_mutex_t`, which comes with your Linux operating system:
```c
#ifdef _POSIX_THREADS
// other code here
#include <pthread.h>
#define PyMUTEX_T pthread_mutex_t
// other code here
```

In Windows 7 and above, you'll see this implementation instead:
```c
#elif defined(NT_THREADS)
#include <windows.h>
typedef SRWLOCK PyMUTEX_T;
```
SRWLOCK documentation: https://docs.microsoft.com/en-us/windows/win32/sync/slim-reader-writer--srw--locks

How is the GIL used?
--------------------

Well, your threads will just try to acquire the lock:

```c
// ceval.c
void
PyEval_AcquireThread(PyThreadState *tstate)
{
    ensure_tstate_not_null(__func__, tstate);

    take_gil(tstate);

    struct _gilstate_runtime_state *gilstate = &tstate->interp->runtime->gilstate;
    if (_PyThreadState_Swap(gilstate, tstate) != NULL) {
        Py_FatalError("non-NULL old thread state");
    }
}
```

Take note a the `take_gil` function - this is the key part that tries to take the lock, otherwise, it will wait (unless it times out, then it asks for the other thread to drop the lock). Some code has been ommitted to highlight the key parts:
```c
// ceval_gil.h
static void
take_gil(PyThreadState *tstate)
{
    // some code ommited here

    /* Check that _PyEval_InitThreads() was called to create the lock */
    assert(gil_created(gil));

    MUTEX_LOCK(gil->mutex);

    if (!_Py_atomic_load_relaxed(&gil->locked)) {
        goto _ready;
    }

    while (_Py_atomic_load_relaxed(&gil->locked)) {
        int timed_out = 0;
        unsigned long saved_switchnum;

        saved_switchnum = gil->switch_number;


        unsigned long interval = (gil->interval >= 1 ? gil->interval : 1);
        COND_TIMED_WAIT(gil->cond, gil->mutex, interval, timed_out);
        /* If we timed out and no switch occurred in the meantime, it is time
           to ask the GIL-holding thread to drop it. */
        if (timed_out &&
            _Py_atomic_load_relaxed(&gil->locked) &&
            gil->switch_number == saved_switchnum)
        {
            SET_GIL_DROP_REQUEST(ceval);
        }
    }

_ready:
    /* We now hold the GIL */
    _Py_atomic_store_relaxed(&gil->locked, 1);
    _Py_ANNOTATE_RWLOCK_ACQUIRED(&gil->locked, /*is_write=*/1);

    // more code ommited here

}
```