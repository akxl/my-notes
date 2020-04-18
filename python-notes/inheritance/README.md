Python Class Inheritance
========================

Summary
-------


Introduction
------------



Simple Inheritance
------------------

```python
class Person:
    def __init__(self, name):
        self.name = name

    def introduce_self(self):
        print('My name is ' + self.name + '.')


# A Student is a Person, so inherit from the Person class.
# Student inherits the `name` attribute and the `introduce_self` method
class Student(Person):
    def __init__(self, name, year):
        super().__init__(name)
        self.year = year
```

```python
if __name__ == '__main__':

    student_1 = Student('Ashley', 6)
    print(student_1.name)  # output: 'Ashley'
    print(student_1.year)  # output: 6
    # call the inherited method
    student_1.introduce_self()  # output: 'My name is Ashley.'
```

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

```python
class PoliteStudent(Student):
    def __index__(self, name, year):
        super().__init__(name, year)

    # original `introduce_self` is overridden: first call the original method, then perform something extra
    def introduce_self(self):
        super().introduce_self()
        print("It's a pleasure to meet you.")
```

```python
if __name__ == '__main__':

    student_2 = PoliteStudent('Bob', 5)
    # call the overridden method
    student_2.introduce_self()  # output: 'My name is Bob.\nIt's a pleasure to meet you.'
```


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

```python
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

```python
if __name__ == '__main__':

    lab_rat = LabRat('Squeak!')
    # call the implemented method
    lab_rat.make_sound()  # output: Squeak!
```

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