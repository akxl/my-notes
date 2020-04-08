import java.util.Iterator;

/**
 * Contrived example of a custom Java Iterable.
 */
public final class MyJavaIterable<T> implements Iterable<T>, Iterator<T> {

    private final T parameter1;
    private final T parameter2;
    private final T parameter3;
    private int parameterCounter = 0;

    public MyJavaIterable(final T parameter1, final T parameter2, final T parameter3) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.parameter3 = parameter3;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (parameterCounter < 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        if (parameterCounter == 0) {
            parameterCounter++;
            return parameter1;
        } else if (parameterCounter == 1) {
            parameterCounter++;
            return parameter2;
        } else if (parameterCounter == 2) {
            parameterCounter++;
            return parameter3;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

}


public final class Main {
    public static void main(final String[] args) {

        /**
         * Case 1: using a Java array
         * Prints:
         * [Array] The number is 1
         * [Array] The number is 2
         * [Array] The number is 3
         */
        final Integer[] myIntegers = { 1, 2, 3 };
        for (final Integer integer : myIntegers) {
            System.out.println(String.format("[Array] The number is %d", integer));
        }

        /**
         * Case 2: using my custom Iterable
         * Prints:
         * [Custom] The number is 1
         * [Custom] The number is 2
         * [Custom] The number is 3
         */
        final Iterable<Integer> myIterable = new MyJavaIterable<>(1, 2, 3);
        for (final Integer integer : myIterable) {
            System.out.println(String.format("[Custom] The number is %d", integer));
        }


    }
}