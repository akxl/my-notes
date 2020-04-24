fun main() {

    // You can have values in Kotlin.
    // Values cannot change their values.
    val integer1 = 1
    val string2 = "This is a string."
    val double2 = 3.146591

    // You can reassign variables, but only to the same type.
    var integer4 = 4
    integer4 = 69
    // This is illegal and won't compile: integer4 = "assign to string"

    // This is a function.
    fun function1(): String {
        return "This function returns this string."
    }

    // This is how you call / 'use' a function.
    function1()

    // You can assign the return value of a function to a value / variable.
    val returnValueOfFunction = function1()
    println(returnValueOfFunction) // prints: "This function returns this string."

    // You can also assign functions to a value / variable.
    val valueThatPointsToFunction1 = { function1() }

    // You can now call the function this way.
    println(valueThatPointsToFunction1())  // Also prints: "This function returns this string."

    // You can have functions with parameters / arguments.
    fun functionWithParameters(parameter1: Int, parameter2: Int): Int {
        return parameter1 + parameter2
    }

    println(functionWithParameters(4, 6))  // prints 10

    // Your functions can have default values.
    fun functionWithDefaultValues(parameter1: Int, parameter2: Int = 7): Int {
        return parameter1 + parameter2
    }

    println(functionWithDefaultValues(4)) // prints 11

    // You can still hold functions that accept multiple parameters in values / variables.
    val myFunction = { a: Int, b: Int -> functionWithDefaultValues(a, b) }

    // And this is how you call it with parameters.
    println(myFunction(6, 9))  // prints 15

    // You can 'cheat' a little by returning tuples to return multiple values.
    fun returnStringAndInteger(): Pair<String, Int> {
        return "return value 1" to 69
    }

    val returnValues = returnStringAndInteger()
    println(returnValues.first)  // prints "return value 1"
    println(returnValues.second)  // prints 69

}

