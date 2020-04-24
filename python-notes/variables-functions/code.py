# This is the equivalent of your 'main' function.
# Things within this would run if you run this script directly.
if __name__ == '__main__':

    # There are no curly braces to denote a block of code.
    # Blocks are denoted by indentation.
    # So code with the same level of indentation belong to the same block.

    # You don't need to predeclare variables.
    # You just start assigning values to variables at declaration.
    integer_1 = 1
    string_2 = 'This is a string'
    float_3 = 3.14159

    # You can reassign variables. Variables are not `final`.
    integer_1 = 2

    # You can also reassign variables with a value of a different type.
    float_3 = 'This is another string.'

    # You can also declare multiple variables at the same time.
    variable1, variable2, variable3 = 2.71828, 69, 'another string'


    # This is a function. You use the `def` keyword to declare a function.
    def function_1():
        return 'This function returns this string.'

    # This is how you call / 'use' a function.
    function_1()

    # You can assign the return value of a function to a variable.
    return_value_of_function = function_1()
    print(return_value_of_function)  # prints 'This function returns this string.'

    # You can also assign functions to a variable.
    # That's right, not the return value - the function itself.
    variable_that_points_to_function = function_1

    # You can now call the function this way.
    print(variable_that_points_to_function())  # Also prints 'This function returns this string.'

    # Your functions can have parameters / arguments
    def function_with_parameters(parameter1, parameter2):
        return parameter1 + parameter2

    print(function_with_parameters(4, 6))  # returns 10

    # Your function parameters can have default values.
    def function_with_defaults(parameter1, parameter2 = 7):
        return parameter1 + parameter2

    print(function_with_defaults(4))  # Only first parameter declared here. Returns 11.

    # You can still hold functions that accept multiple parameters in variables.
    my_function = function_with_defaults

    # And this is how you call it with parameters.
    print(my_function(6, 9))  # prints 15

    # Functions can return multiple values.
    def return_string_and_integer():
        return 'return value 1', 69

    return_value_1, return_value_2 = return_string_and_integer()  # You can assign each return value to its own variable
    print(return_value_1)  # prints 'return value 1'
    print(return_value_2)  # prints 69
