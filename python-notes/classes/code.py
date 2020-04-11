# Demonstration of python classes
from collections import namedtuple


# the most basic class that has nothing in it
# assign fields after initialisation
class StudentVersion0:
    pass


# class with an initializer (or a 'constructor')
class StudentVersion1:

    # initializer - use this to initialise fields
    # like using a constructor
    # strictly isn't a constructor, but you can call it that if it helps you
    def __init__(self, name, id_number, year):
        self.name = name
        self.id_number = id_number
        self.year = year


# Using a namedtuple from the collections module.
# Strictly speaking, namedtuples and classes have different implementations.
# They also have different performance and memory characteristics.
# But perfectly usable nonetheless.
StudentVersion2 = namedtuple('StudentVersion2', ['name', 'id_number', 'year'])


# A 'data class' or the POJO equivalent
# with hash and equals methods.
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


if __name__ == '__main__':

    # Case 0: initialise, then assign.
    student_0 = StudentVersion0()
    student_0.name = 'Amelia'
    student_0.id_number = 1001
    student_0.year = 6

    print(student_0.name)  # output: 'Amelia'
    print(student_0.id_number)  # output: 1001
    print(student_0.year)  # output: 6

    # Case 1: assign using initializer method
    student_1 = StudentVersion1('Bart', 1002, 5)

    print(student_1.name)  # output: 'Bart'
    print(student_1.id_number)  # output: 1002
    print(student_1.year)  # output: 5

    ## I can still assign other non-declared fields
    student_1.favourite_subject = 'geography'
    print(student_1.favourite_subject)  # output: 'geography'

    # Case 2: using a named tuple
    student_2 = StudentVersion2('Carla', 1003, 7)

    print(student_2.name)  # output: 'Carla'
    print(student_2.id_number)  # output: 1003
    print(student_2.year)  # output: 7

    ## I cannot assign another attribute. The code below throws an AttributeError.
    ## student_2.favourite_subject = 'maths'

    # Case 3: using a 'data class'
    student_3 = StudentVersion3('Daniel', 1004, 8)
    print(hash(student_3))  # calls __hash__()
    print(student_3 == student_3)  # output: True
    print(student_3 == student_2)  # output: False
    print(student_3 == 'random string')  # output: False
