Python Class Methods
====================

Summary
-------

1. Python classes can have instance and static methods.
2. Static methods are quite similar to Java static methods.
3. Python instance method expects the `this`-equivalent to be passed into the function as the _first argument_ for it to be able to access the data stored in the object. `this` is typically named as `self` as per _convention_.


Introduction
------------

This document aims to demonstrate how one can create instance methods and static methods for a given python class, the the close-enough Java equivalents for comparison.

Instance Methods
---------------

In general, regardless of language, an instance method is used to perform some function in the context of the stored instance variables. If your function doesn't depend on the instance data, you probably don't need instance methods.

In python, an instance method is defined inside a class, just like in Java. In java, an instance method can access the object's fields using the `this` keyword, as shown in the line `nameStoredInObject = this.name` in the code below:
```java
public class StudentVersion0 {

    public String name;

    public void introduceSelf() {
        String nameStoredInObject = this.name;
        System.out.println(String.format("Hi, my name is %s.", nameStoredInObject));
    }
}
```

Python can do the same thing, but it is a bit special. When _defining_ the instance method (not the calling), you must explicitly pass the `this`-equivalent into the function as the _first argument_. Only then can the instance method access the data stored in the instance. Conventionally, the `this` equivalent is named `self`, but it can be nammed anything, as long as it is the first argument to the function.
```python
class StudentVersion0:

    def __init__(self, name):
        self.name = name

    # this is the instance method
    def introduce_self(self):
        print('Hi, my name is {}.'.format(self.name))
```

To call the method, you just call it like any other normal python function (you do not need to manually pass the object instance in. That is done for you.)
```python
student = StudentVersion0('Alice')
student.introduce_self()  # prints 'Hi, my name is Alice.'
```

Static Methods
-------------

The Python static method is similar to the Java static method, both in its definition and in the calling. You just have to 'annotate' it with the `@staticmethod` Python _decorator_ (another topic for future discussion).

```python
class StudentVersion0:

    @staticmethod
    def worry_about_student_loans():
        print('Pls halp. Gib job.')

```

To call the method, you have to call it with its class name as shown below. You do not need a preexisting instance.
```python
StudentVersion0.worry_about_student_loans()  # prints 'Pls halp. Gib job.'
```


Combined, with Java Comparison
------------------------------

Here's the python version:
```python
class StudentVersion0:

    def __init__(self, name):
        self.name = name

    # A method should only be needed if the function relies on the state
    # stored within the individual object.
    # E.g. each student would introduce themselves differently, depending on their name.
    def introduce_self(self):
        print('Hi, my name is {}.'.format(self.name))

    # A static method does not depend on the individual instance
    # It should be a function that any Student is expected to do
    # You generally call it using the class name to qualify it
    # e.g. ClassName.static_method_name()
    @staticmethod
    def worry_about_student_loans():
        print('Pls halp. Gib job.')

```

And here's the Java equivalent:
```java
// Python classes are not final
public class StudentVersion0 {

    // Public and mutable
    public String name;

    public StudentVersion0(String name) {
        this.name = name;
    }

    public void introduceSelf() {
        System.out.println(String.format("Hi, my name is %s.", this.name));
    }

    public static void worryAboutStudentLoans() {
        System.out.println("Pls halp. Gib job.");
    }

}
```


Note: there is something else called a 'classmethod', but I'm not going to go into it.