Python Iterables
================

Summary
-------

* In Python, as in Java, the `for` loop plays very nicely with iterables.
* Lists, sets and other collections are examples of built-in iterables. 
* If you want to write your own custom iterable in Python, you just have to define the `__iter__` and `__next__` methods.


Introduction
------------

This document introduces the Python iterable, and its close-enough equivalent in Java. Thankfully the iterable is general enough a concept that it should be a thing in many languages.

Generically, an iterable is something that can be iterated on. For example, a door bell is something that you can press many times / iterably. The outcome of that is that the door bell rings (assuming that it works). A light switch might be another iterable, but each iteration gives you one of two outcomes - on or off. A one-armed bandit is also an iterable, just that in this case, the outcome of each play is more varied than that of a door bell or a light switch.

In the cases above, the iterables are infinite - you can use them as many times as you want or are able to. For the example that I am going to use here, I am going to use finite iterables just to make things simpler.


Simple Example
--------------

In Python, one of the most common iterables is the list, for example, here is a list of the integers 1, 2 and 3:
```python
my_integers = [1, 2, 3]
```

If I would like to print each of them one by one (i.e. iterably), I can choose to use the `for` loop like so:
```python
my_integers = [1, 2, 3]
for integer in my_integers:
    print('[Python List] The number is: {}'.format(integer))
```
which would print
```
[Python List] The number is: 1
[Python List] The number is: 2
[Python List] The number is: 3
```
The `for` loop takes each integer and prints them out one at a time.

A simple Java equivalent can be demonstrated with Java arrays:
```java
final Integer[] myIntegers = { 1, 2, 3 };
for (final Integer integer : myIntegers) {
    System.out.println(String.format("[Java Array] The number is: %d", integer));
}
```
which would print
```
[Java Array] The number is: 1
[Java Array] The number is: 2
[Java Array] The number is: 3
```


A More Generic Example
----------------------

Python lists and Java arrays are not the only built-in iterables. In Python, sets and dictionaries are examples of iterables. In Java, you have `List`s, `Set`s, `Map`s are iterables. But what makes them iterables? (at least, at the language level?)

It turns out that in Python, any class that has the `__iter__` AND `__next__` methods defined _is_ an iterable. That's the agreed upon definition of a Python iterable. More specifically, the `__iter__` method returns an iterator that allows you to do the actual iteration on (and allows the `for` loop to work internally). The _iterator_ has the `__next__` method defined - each time this method is called, it returns the next item in line. In many cases like this, the iterable and the iterator can be the same object, in which case, the `__iter__` method simply returns the object itself. Splitting the iterable and the iterator up can give more flexibility in more complex domains, which we will not go into now.

So below we have a very extremely contrived custom Python iterable. I load it up with 3 integers, and then I call each of them out using the `for` loop:
```python
class MyPythonIterable:

    counter = 0

    def __init__(self, parameter1, parameter2, parameter3):
        self.parameter1 = parameter1
        self.parameter2 = parameter2
        self.parameter3 = parameter3

    def __iter__(self):
        return self

    def __next__(self):
        if self.counter == 0:
            self.counter = self.counter + 1
            return self.parameter1
        elif self.counter == 1:
            self.counter = self.counter + 1
            return self.parameter2
        elif self.counter == 2:
            self.counter = self.counter + 1
            return self.parameter3
        else:
            raise StopIteration


# using custom Python iterable
my_iterable = MyPythonIterable(1, 2, 3)
for integer in my_iterable:
    print('[MyPythonIterable] The number is: {}'.format(integer))
```
The `StopIteration` is used to denote that the iterable has been exhausted and it tells the `for` loop to stop. This prints:
```
[MyPythonIterable] The number is: 1
[MyPythonIterable] The number is: 2
[MyPythonIterable] The number is: 3
```

We have now created our own code that can interface nicely with the built-in `for` loop.

The same can be done in Java, allowing us to use the built-in `for` loop too. To achieve the same effect, our Java class would implement:
* the `Iterable` interface - this gives us the `iterator()` method, which corresponds to the `__iter__` method; and
* the `Iterator` interface - this gives us the `next()` method, which corresponds to the `__next__` method, along with the `hasNext()` method, which serves to tell the us (or the `for` loop) when to stop, just like the `StopIteration` in Python.
```java
public final class MyJavaIterable<T> implements Iterable<T>, Iterator<T> {

    private final T parameter1;
    private final T parameter2;
    private final T parameter3;
    private int counter = 0;

    public MyJavaIterable(final T parameter1, final T parameter2, final T parameter3) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.parameter3 = parameter3;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (counter < 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        if (counter == 0) {
            counter++;
            return parameter1;
        } else if (counter == 1) {
            counter++;
            return parameter2;
        } else if (counter == 2) {
            counter++;
            return parameter3;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
```

When we run this in a `for` loop, it would give us similar results:
```java
final Iterable<Integer> myIterable = new MyJavaIterable<>(1, 2, 3);
for (final Integer integer : myIterable) {
    System.out.println(String.format("[MyJavaIterable] The number is: %d", integer));
}
```
This prints:
```
[MyJavaIterable] The number is: 1
[MyJavaIterable] The number is: 2
[MyJavaIterable] The number is: 3
```

And there you have it.
