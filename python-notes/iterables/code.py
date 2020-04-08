# Contrived Python iterable
# Implements __iter__ and __next__
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


if __name__ == '__main__':

    # using standard Python list
    my_integers = [1, 2, 3]
    for integer in my_integers:
        print('[List] The number is: {}'.format(integer))

    # using custom Python iterable
    my_iterable = MyPythonIterable(1, 2, 3)
    for integer in my_iterable:
        print('[Custom] The number is: {}'.format(integer))
