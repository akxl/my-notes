Python Classes
==============

Summary
-------

1. Python is a multi-paradigm language, which also includes object-oriented programming.
2. In Python, you can create classes that store instance data (called attributes in Python).
3. You can also implement methods to fulfill desired contracts, just like implementing an `interface` in Java.


Introduction
------------

This document aims to demonstrate how one can create python classes. I have also included the close-enough Java equivalents for comparison, which I hope will help those who come from a Java-like background pick up the Python programming language.


Simplest Class
--------------

Here is the simplest Python class that you can have:
```python
class StudentVersion0:
    pass
```

This is effectively an empty class. If this were a compiled language, you would probably use this class as a wrapper class for labelling your sub-classes. In a dynamic language like python, however, you can use it as a namespace for you to store anything in _after_ the definition of the class has been declared. For example, I can assign attributes (class fields are called "attributes" in python) on the fly like so:
```python
# declare the definition of the class here
class StudentVersion0:
    pass


# the rough equivalent of the main() function in other languages
if __name__ == '__main__':

    # Case 0: initialise, then assign.
    student_0 = StudentVersion0()
    student_0.name = 'Amelia'
    student_0.id_number = 1001
    student_0.year = 6

    print(student_0.name)  # output: 'Amelia'
    print(student_0.id_number)  # output: 1001
    print(student_0.year)  # output: 6
```

In short, there is no need to pre-declare the fields/attributes in python classes.

There is no Java equivalent of this.


Classes with Initialising Method
------------------------------

In Java, we would probably choose to instantiate an object and also feeding in the object's initial state using a constructor. You can do so too in python, although instead of using a constructor, we would be using an initialisation method defined in the class. The initialisation method (`__init__`) is as shown below:
```python
class StudentVersion1:

    def __init__(self, name, id_number, year):
        self.name = name
        self.id_number = id_number
        self.year = year
```

Strictly speaking, python classes have constructors. We just don't usually call them directly. It's named `__new__`. The constructor instantiates the object, _then_ the initialisation method gets called to populate the object. The new instance gets passed into the initialisation method via the `self` parameter as shown above. This doesn't have to be named `self`, but it's just convention. It's more or less equivalent to `this` in Java. So technically, python class methods are normal functions, the difference is that in the declaration of the function, the first parameter that gets passed in is the instance of the object to be operated on.

I can now pass in the initial values during instantiation as shown below. However, we can also still assign other attributes to it after initialisation, e.g. `favourite_subject` as shown below:
```python
if __name__ == '__main__':

    # Case 1: assign using initializer method
    student_1 = StudentVersion1('Bart', 1002, 5)

    print(student_1.name)  # output: 'Bart'
    print(student_1.id_number)  # output: 1002
    print(student_1.year)  # output: 5

    ## I can still assign other non-declared fields
    student_1.favourite_subject = 'geography'
    print(student_1.favourite_subject)  # output: 'geography'
```

For completeness, the Java equivalent would be using a constructor. However, the dynamic attribute assignment as no equivalent.
```java
public class StudentVersion1 {

    // All public and mutable
    public String name;
    public int idNumber;
    public int year;

    public StudentVersion1(String name, int idNumber, int year) {
        this.name = name;
        this.idNumber = idNumber;
        this.year = year;
    }

}
```


Namedtuples as a Substitute
---------------------------

There is a way to create an object with specified attributes and no more. That is via the `collection.namedtuple()`:
```python
from collections import namedtuple

StudentVersion2 = namedtuple('StudentVersion2', ['name', 'id_number', 'year'])

if __name__ == '__main__':
    # Case 2: using a named tuple
    student_2 = StudentVersion2('Carla', 1003, 7)

    print(student_2.name)  # output: 'Carla'
    print(student_2.id_number)  # output: 1003
    print(student_2.year)  # output: 7

    ## I cannot assign another attribute. The code below does NOT work and throws an AttributeError.
    ## This is how you can limit the attributes your users are allowed to add.
    student_2.favourite_subject = 'maths'
```

Note that the underlying implementations of a python class and a namedtuple are different. As a result, they would have different performance and memory characteristics, but otherwise you can use namedtuples instead of classes that just store data, e.g. a Data Transfer Object (DTO). There is no direct Java equivalent of a namedtuple.


Classes with equals and hash methods
------------------------------------

Let's say you want a complete entity object. You would want to implement the equals and hash methods. To do that, implement the `__hash__` and `__eq__` methods.

```python
class StudentVersion3:
    def __init__(self, name, id_number, year):
        self.name = name
        self.id_number = id_number
        self.year = year

    # implement __hash__ to make this class a 'hashable'
    def __hash__(self):
        # the hash() function only accepts one argument
        # so, I am wrapping the attributes up into a tuple
        return hash((self.name, self.id_number, self.year))

    # implement __eq__ to make this class a 'hashable'
    def __eq__(self, other):
        return (
            other is not None and
            self.__class__ == other.__class__ and
            self.name == other.name and
            self.id_number == other.id_number and
            self.year == other.year
        )
```

Now we are testing the `__hash__` and `__eq__` methods to prove that they work. Note that:
1. the standalone (or 'static' in Java terms) `hash()` function calls the overriden `__hash__` method; and
2. the `==` operator calls the overriden `__eq__` method.
This is a flavour of how polymorphism works in Python.

```python
if __name__ == '__main__':
    # Case 3: using a 'data class'
    student_3 = StudentVersion3('Daniel', 1004, 8)
    print(hash(student_3))  # calls __hash__()
    print(student_3 == student_3)  # output: True
    print(student_3 == student_2)  # output: False
    print(student_3 == 'random string')  # output: False
```

For completeness, here's the Java POJO:
```java
// Python classes are not final
public class StudentVersion3 {

    // All public and mutable
    public String name;
    public int idNumber;
    public int year;

    public StudentVersion3(String name, int idNumber, int year) {
        this.name = name;
        this.idNumber = idNumber;
        this.year = year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.idNumber, this.year);
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass() == this.getClass()) {
            final StudentVersion3 o = (StudentVersion3) other;
            return this.name.equals(o.name) &&
                    this.idNumber == o.idNumber &&
                    this.year == o.year;
        } else {
            return false;
        }
    }
}
```

So there you have it.