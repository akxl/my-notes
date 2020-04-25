Variables and Functions
=======================

Introduction
------------
This document intends to introduce Python variables and functions to a programmer who may have grown up all their life programming in Java. We will show how Python works on a practical level without neccesarily going into the internal operational details, all the while showing the close-enough Java equivalents for comparison.

Variables
---------

### Declaration and assignment
In Java and in many other languages, you can declare a variable before assigning any value to it. For example in Java, one can do this:
```java
// Case 1 - Assignment can be separate from the variable's declaration.
int integer1;
integer1 = 1;
```

Of course, you can also assign a value on the same line as a variable declaration:
```java
// Case 2 - Assignement happens on the same line as the declaration of the variable.
int integer3 = 4;
String string3 = "This is a string";
double double4 = 3.141591;
```

In Python, you don't declare a variable and perform the assignment on a different line. You have to assign a value at declaration:
```python
integer_1 = 1
string_2 = 'This is a string'
float_3 = 3.14159
```

You can also choose to assign multiple variables of different types in the same line, although whether you want to do it for readability reasons is completely up to you.
```python
variable1, variable2, variable3 = 2.71828, 69, 'another string'  # completely legal
```

### Reassignment
In Java, variables can be reassigned a new value if:
1. the new value is of the same type; and
2. the variable is not `final`.
```java
// Case 1 - Assign a new integer to an integer variable.
int integer1;
integer1 = 1;
integer1 = 2;

// Case 2 - ERROR - you cannot assign a differnt type (well, there are many ways to coerce reassignment, sometimes for legitimate reasons, but let's keep it simple.)
integer1 = "Assigning a string."; // will not compile

// Case 3 - ERROR - you cannot reassign a `final`-ised variable.
final int integer2 = 7;
integer2 = 6; // will not compile
```

In Python, you can pretty much reassign anything you want:
```python
integer_1 = 1
integer_1 = 'Assigning a string.'  # completely fine
integer_1 = 3.14159  # completely fine too
integer_1 = SomeObject()  # yeap
```


Functions
---------

### A simple function
Unlike Java, your function doesn't have to be part of a class - you can have free standing functions like in C or Kotlin or nearly every other language. If you grew up with Java all your life, just treat these free standing functions like `static` methods.

A function is declared using the `def` keyword. Here is a function that takes 0 parameters and returns a string:
```python
def function_1():
    return 'This function returns this string.'
```

You call / use a function like you would in Java:
```python
# some code up here
function_1()
# more code down here
```

If a function returns a value, you can assign the return value to a variable:
```python
return_value_of_function = function_1()
print(return_value_of_function)  # prints 'This function returns this string.'
```

### Multiple return values
One neat thing about Python functions is that you can easily return multiple values without much fuss:
```python
def return_string_and_integer():
        return 'return value 1', 69

return_value_1, return_value_2 = return_string_and_integer()  # You can assign each return value to its own variable
print(return_value_1)  # prints 'return value 1'
print(return_value_2)  # prints 69
```
As shown above, you can assign each return value to its own variable in the same line as the call to the function.

To be able to return multiple values in Java, you would probably have to use a wrapper class and return an instance of that wrapper with the values that you want. And you would have to access each return value separately.
```java
// A possible alternative would be to use https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/tuple/Pair.html
// For simplicity, I'm not going to bother with writing getters (they are final and immutable types anyway).
public final class Wrapper {
    public final String value1;
    public final int value2;
    public Wrapper(final String value1, final int value2) {this.value1 = value1; this.value2 = value2;}
}

public Wrapper returnStringAndInteger() {
    return new Wrapper("return value 1", 69);
}

final Wrapper result = returnStringAndInteger();
System.out.println(result.value1)  // prints "return value 1"
System.out.println(result.value2)  // prints 69
```

### Functions with parameters
Of course, you can accept parameters in Python functions:
```python
def function_with_parameters(parameter1, parameter2):
    return parameter1 + parameter2

print(function_with_parameters(4, 6))  # returns 10
```

You can also set default values for your parameters. That way, you don't have to call your function with so many parameters.
```python
def function_with_defaults(parameter1, parameter2 = 7):
    return parameter1 + parameter2

print(function_with_defaults(4))  # Only first parameter declared here. Returns 11.
```

To achieve this in Java, you would probably have to rely on overloaded methods:
```java
int someFunction(int parameter1) {
    return someFunction(parameter1, 7);
}

int someFunction(int parameter1, int parameter2) {
    return parameter1 + param2;
}

System.out.println(someFunction(4)); // prints 11
```

Or less elegantly and may be considered a code smell today, you have null checks:
```java
int functionWithDefaults(int parameter1, Integer parameter2) {
    int param2 = parameter2 != null ? parameter2 : 7;
    return parameter1 + param2;
}

System.out.println(functionWithDefaults(4, null)); // prints 11
```

### Functions are objects that can be assigned to variables
In Python, you can easily assign functions to variables of function parameters. This is also true for many other languages including to a certain extent Java. But it's just less fuss in Python compared to Java. For example:
```python
def function_1():
    return 'This function returns this string.'

# You can assign functions to variables.
# That's right, not the return value - the function itself.
variable_that_points_to_function = function_1

# You can now call the function this way.
print(variable_that_points_to_function())  # Also prints 'This function returns this string.'
```

If you wanted to do this in Java, you would need a lot more code than that:
```java
static final String function1() {
    return "This function returns this string.";
}

// You can assign the function to another variable. In this case, since it has 0 inputs and a string output,
// this variable is of type `Supplier<String>`.
final Supplier<String> variableThatPointsToFunction = VarFunc::function1;

// Now you can call the function this way.
System.out.println(variableThatPointsToFunction.get()); // also prints "This function returns this string."
```

You can extend this idea to functions that accept parameters too:
```python
def function(parameter1, parameter2):
    return parameter1 + parameter2

# You can still hold functions that accept multiple parameters in variables.
my_function = function

# And this is how you call it with parameters.
print(my_function(6, 9))  # prints 15
```

In Java, this would be look like this:
```java
static final int functionWithParameters(int parameter1, int parameter2) {
    return parameter1 + parameter2;
}
        
// You can still hold functions that accept multiple parameters in variables.
// In this case, since this function accepts 2 integer parameters, and returns an integer,
// the variable's type is `BinaryOperator<Integer>`.
final BinaryOperator<Integer> myFunction = VarFunc::functionWithParameters;

// And this is how you call it with parameters.
System.out.println(myFunction.apply(6, 9));  // prints 15
```