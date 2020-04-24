import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class VarFunc {

    public static void main(String[] args) {

        // You can declare variables and assign them at a different step.
        int integer1;
        integer1 = 1;

        // You can also reassign values to variables,
        // as long as they are of the same type.
        integer1 = 2;

        // Finalised variables cannot have their values reassigned.
        final int integer2 = 3;
        // this is illegal: integer2 = 4

        // You can declare and assign variables at the same time.
        int integer3 = 4;
        String string3 = "This is a string";
        double double4 = 3.141591;

        // The function is called this way.
        function1();

        // You can assign the return value of a function to a variable.
        String returnValue = function1();

        System.out.println(returnValue);  // prints "This function returns this string."

        // You can also assign the function to another variable
        Supplier<String> variableThatPointsToFunction = VarFunc::function1;

        // Now you can call the function this way.
        System.out.println(variableThatPointsToFunction.get()); // also prints "This function returns this string."

        // Your functions can have parameters / arguments.
        System.out.println(functionWithParameters(4, 6));  // prints 10

        // Your functions can have default values using overloads and/or null checks.
        System.out.println(functionWithDefaults(4));  // prints 11

        // You can still hold functions that accept multiple parameters in variables.
        BinaryOperator<Integer> myFunction = VarFunc::functionWithParameters;

        // And this is how you call it with parameters.
        System.out.println(myFunction.apply(6, 9));  // prints 15

    }

    static String function1() {
        return "This function returns this string.";
    }

    static int functionWithParameters(int parameter1, int parameter2) {
        return parameter1 + parameter2;
    }

    static int functionWithDefaults(int parameter1) {
        return functionWithDefaults(parameter1, null);
    }

    static int functionWithDefaults(int parameter1, Integer parameter2) {
        int param2 = parameter2 != null ? parameter2 : 7;
        return parameter1 + param2;
    }

}
