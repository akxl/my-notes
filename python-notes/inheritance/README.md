Python Class Inheritance
========================

Summary
-------

* Python supports object-oriented programming
* Classes can be defined and classes may inherit other classes in Python.

Introduction
------------

This document aims to demonstrate simply how class inheritance is done in Python, and we will also show you the close-enough Java equivalents of the Python code used.


Simple Inheritance
------------------

Say that the overall project was to model a school. Your specific task was to model the people who you would expect to be in a school.

As a generalisation, you first model a generic person. A person is expected to have a `name`, and the person has the ability to introduce themselves. So you model like so:
```python
class Person:
    def __init__(self, name):
        self.name = name

    def introduce_self(self):
        print('My name is ' + self.name + '.')
```

Okay, we now have a model of a person, we would now like to be more specific about the people in our school. So, you decide to start modelling a student. Each student has a name, and has the ability to introduce themselves. But you have already written the code for these, and you want to reuse them. So would somehow want to 'inherit' them. Additionally, you want to store the Year that the student is in. You come up with this:
```python
# A Student is a Person, so inherit from the Person class.
# Student inherits the `name` attribute and the `introduce_self` method
class Student(Person):
    def __init__(self, name, year):
        super().__init__(name)
        self.year = year
```

We declare that `Student` inherits from `Person` using `class Student(Person)`. This way, the `name` attribute and the `introduce_self` are inherited and you don't need to rewrite them.

In the initialiser (i.e. the `__init__` method), you would need to feed the `name` attribute to the parent's initialiser. You do that by calling `super().__init__()`. In simplistic terms, `super()` refers to the parent that you have inherited from, and you call its initialiser with the standard `.__init__()` call. For the `year` attribute, which is something extra that we want that is specific to the student, we assign the attribute like usual.

When we run the code, we can expect to see that our `Student` has inherited the `name` attribute and the `introduce_self` method, as well as having our newly defined `year` attribute:
```python
if __name__ == '__main__':

    student_1 = Student('Ashley', 6)
    print(student_1.name)  # output: 'Ashley'
    print(student_1.year)  # output: 6
    # call the inherited method
    student_1.introduce_self()  # output: 'My name is Ashley.'
```

Here's the near-enough Java equivalent of a `Student` inheriting a `Person`:
```java
public class Person {
    // attributes in Python are public and mutable
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public void introduceSelf() {
        System.out.println("My name is " + this.name + ".");
    }
}

public class Student extends Person {
    public int year;

    public Student(String name, int year) {
        super(name);
        this.year = year;
    }
}
```


Overriding Methods
------------------

Let's say that you want to create a subclass of students who _do_ have an `introduce_self` method, but you don't want the standard implementation. You want to modify it, or change it completely. That's called overriding a method. In Python, overriding a method is as simple as just defining the method again with a `def` as if the original method doesn't exist.

However, if you would like the original code to run too, once again, you call the parent using `super()`, followed by the call to the original method. For example, here's an example of a very polite student who, when introducing themselves, not only tells everyone else their name, they would also say "It's a pleasure to mee you" afterwards:
```python
class PoliteStudent(Student):
    def __index__(self, name, year):
        super().__init__(name, year)

    # original `introduce_self` is overridden: first call the original method, then perform something extra
    def introduce_self(self):
        super().introduce_self()
        print("It's a pleasure to meet you.")
```

If we now run the code, we can see that the method that is being run is indeed our overriden method:
```python
if __name__ == '__main__':

    student_2 = PoliteStudent('Bob', 5)
    # call the overridden method
    student_2.introduce_self()  # output: 'My name is Bob.\nIt's a pleasure to meet you.'
```

Here is the close-enough Java equivalent of overrding a method:
```java
public class PoliteStudent extends Student {
    public PoliteStudent(String name, int year) {
        super(name, year);
    }

    @Override
    public void introduceSelf() {
        super.introduceSelf();
        System.out.println("It's a pleasure to meet you.");
    }
}
```


Abstract Classes
----------------

So, what if you wanted abstract classes? Maybe you want some methods to be shared, but other methods need to be defined according to the individual subclasses. Or maybe you just want a contract written down and you want to somehow have a better guarantee that your classes implement certain methods (like an interface in Java-like languages). This section would show you how to have abstract methods in your Python code.

To declare an abstract class, you would need your abstract class to inherit the `abc.ABC` class. You then declare abstract methods using the `@abstractmethod` decorator.

To use a abstract class, just inherit it and override the method like normal. Here is an example:
```python
from abc import ABC, abstractmethod

class AbstractAnimal(ABC):
    @abstractmethod
    def make_sound(self):
        pass


class LabRat(AbstractAnimal):
    def __init__(self, sound):
        self.sound = sound

    # I am now implementing the `make_sound` method
    def make_sound(self):
        print(self.sound)
```

Now when you run the code, you will see that your `LabRat` class does indeed have a `make_sound` method with its own specific implementation.
```python
if __name__ == '__main__':

    lab_rat = LabRat('Squeak!')
    # call the implemented method
    lab_rat.make_sound()  # output: Squeak!
```

Here's a close-enough Java equivalent:
```java
// Arguably, this contrived example can be an `Interface`
public abstract class AbstractAnimal {
    abstract public void makeSound();
}

public class LabRat extends AbstractAnimal {
    // attributes in Python are public and mutable
    public String sound;

    public LabRat(String sound) {
        this.sound = sound;
    }

    @Override
    public void makeSound() {
        System.out.println(sound);
    }
}
```