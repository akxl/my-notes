class StudentVersion0:
    def __init__(self, name):
        self.name = name


# relies on the object having the 'name' attribute
def get_student_name(student):
    print(student.name)


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


if __name__ == '__main__':

    # Case 0: getting the student's name from the attribute directly
    student_0 = StudentVersion0('Angela')
    get_student_name(student_0)  # output: 'Angela'

    # Case 1: getting the student's name from a getter rather than directly
    student_1 = StudentVersion1('Bob')
    get_student_name(student_1)  # output: 'Called from name property: Bob'

    # Case 2: using a setter instead of directly assigning to the attribute
    student_2 = StudentVersion2()
    student_2.name = 'Candy'
    get_student_name(student_2)  # output: 'Comrade Candy'
