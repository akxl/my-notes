from abc import ABC, abstractmethod


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


class PoliteStudent(Student):
    def __index__(self, name, year):
        super().__init__(name, year)

    # original `introduce_self` is overridden: first call the original method, then perform something extra
    def introduce_self(self):
        super().introduce_self()
        print("It's a pleasure to meet you.")


# Completely abstract class with no implementation.
# Abstract classes are defined by inheriting `abc.ABC`.
# Value is not really apparent in small code bases, as you can just
# mentally make sure that every object has `make_sound` defined and implemented
# without the overhead of this declaration. However, abstract classes become extremely
# useful in large code bases, and when paired with static typing libraries
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


if __name__ == '__main__':

    student_1 = Student('Ashley', 6)
    print(student_1.name)  # output: 'Ashley'
    print(student_1.year)  # output: 6
    # call the inherited method
    student_1.introduce_self()  # output: 'My name is Ashley.'

    student_2 = PoliteStudent('Bob', 5)
    # call the overridden method
    student_2.introduce_self()  # output: 'My name is Bob.\nIt's a pleasure to meet you.'

    lab_rat = LabRat('Squeak!')
    # call the implemented method
    lab_rat.make_sound()  # output: Squeak!
