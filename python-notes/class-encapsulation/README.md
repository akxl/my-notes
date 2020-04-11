Python Class Encapsulation
==========================

Summary
-------

1. Be default, class attributes are accessed directly.
2. Getters and setters can be retrofitted in easily via the `@property` decorator without breaking your API should the need arise.


Introduction
------------

This document aims to show that you can introduce forms of encapsulation on your classes with getters and setters. The getters and setters can be flexibly introduced at a later stage when it is truly neccesary without breaking your API.

Simulated Client Code
---------------------

Imagine that our client wants us to provide with an object that represents a student. And within that object, the client expects that the `name` attribute exists:
```python
# relies on the object having the 'name' attribute
def get_student_name(student):
    print(student.name)

```

So from now on, we would have to code against this expectation. Our aim is to give the client exactly what they expect with as little pain to them as possible. They pay us to make thier problems go away, and that is exactly what they are going to get.


Base Class Without Getters and Setters
--------------------------------------

So let's say that in version 0, the very first version that we ship to our clients - it's just a simple Student class that stores the student's name. The client is able to retrieve that name by accessing the property via `object.name`, which fulfills the contract:
```python
class StudentVersion0:
    def __init__(self, name):
        self.name = name
```
Here is the client running their code:
```python
if __name__ == '__main__':

    # Case 0: getting the student's name from the attribute directly
    student_0 = StudentVersion0('Angela')
    get_student_name(student_0)  # output: 'Angela'
```
Here's the Java equivalent of the python class that we wrote:
```java
public class StudentVersion0 {

    // Public and mutable
    String name;

    public StudentVersion0(String name) {
        this.name = name;
    }
}
```


Getters via `@property`
-----------------------

Let's say now that the client wishes to, for whatever reason, have a string prepended to the front of the name whenever they get the name. They wish that the changes be made on our end and are going to pay us for that work. They don't want to make any changes on their code base, therefore, they are still going to access the student's name from `object.name`.

What are we going to do? Isn't that a direct access to the `name` attribute?

Thankfully in python, there is the `@property` decorator that can help. We can rename the actual attribute to something else, say, '_name' instead of 'name', and use a function to represent the call to `object.name` instead. That way, we can have our own custom response without breaking our promise to our client.

```python
# Introducing a getter using the @property syntactic sugar
class StudentVersion1:

    # Renaming self.name to self._name to refer to the attribute that stores the name internally
    # so that it would not clash with the new getter that we have defined.
    # We want the getter to be called 'name' so that we still fulfill the contract that we have
    # with our client (in this case, it's the get_student_name function) so that our client
    # can continue using their functions without any interruption
    def __init__(self, name):
        self._name = name

    @property
    def name(self):
        return 'Called from name property: ' + self._name
```

Here is the client calling our code. The same old client function works as expected.
```python
if __name__ == '__main__':

    # Case 1: getting the student's name from a getter rather than directly
    student_1 = StudentVersion1('Bob')
    get_student_name(student_1)  # output: 'Called from name property: Bob'
```

What this amounts to is to create a getter, just like in Java:
```java
public class StudentVersion1 {

    // Python attributes are public and mutable
    // even if they have an underscore in front of them
    public String _name;

    public StudentVersion1(String name) {
        this._name = name;
    }

    public String getName() {
        return "Called from name property: " + this._name;
    }

}
```

This shows us that, in python, we have the flexibility to expose our attributes for our early versions, and easily modify the implementation only when neccesary.


Setters
-------

Just as we can retrofit a getter, we can also do the same with a setter. Now imagine that our client is anarcho-syndicalist commune (obligatory Monty Python reference) and wishes to have each student be called by the title 'Comrade'. They also want this title stored automatically upon setting. This is where setters come in.

Using the `@property` decorator to create our getter also allows us to create our setter. After declaring our getter, we simply add another overloaded function to represent the setter method along with the `@<property_name>.setter` decorator:
```python
# introducing a setter
class StudentVersion2:

    def __init__(self):
        self._name = None

    @property
    def name(self):
        return self._name

    # the @property must be declared before this
    # use an overload of name()
    @name.setter
    def name(self, name):
        self._name = 'Comrade ' + name
```

And we can see that the setter works:
```python
if __name__ == '__main__':

    # Case 2: using a setter instead of directly assigning to the attribute
    student_2 = StudentVersion2()
    student_2.name = 'Candy'
    get_student_name(student_2)  # output: 'Comrade Candy'
```

This is the equivalent of defining a setter in Java like so:
```java
public class StudentVersion2 {

    // Python attributes are public and mutable
    // even if they have an underscore in front of them
    public String _name;

    public StudentVersion2(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = "Comrade " + name;
    }

}
```