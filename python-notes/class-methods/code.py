# Demonstration of custom class methods


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
