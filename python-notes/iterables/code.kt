/**
 * Contrived Kotlin Iterable
 */
class MyKotlinIterable<T>(
        private val parameter1: T,
        private val parameter2: T,
        private val parameter3: T) : Iterable<T>, Iterator<T> {

    private var counter = 0

    override fun iterator(): Iterator<T> {
        return this;
    }

    override fun hasNext(): Boolean {
        return counter < 3
    }

    override fun next(): T {
        return when (counter) {
            0 -> {
                counter++
                parameter1
            }
            1 -> {
                counter++
                parameter2
            }
            2 -> {
                counter++
                parameter3
            }
            else -> throw IndexOutOfBoundsException()
        }
    }

}


fun main() {

    // Using a Kotlin List as an example
    val list = listOf(1, 2, 3)
    for (item in list) {
        println("[List] The number is: $item")
    }

    // Using my custom Kotlin Iterable
    for (item in MyKotlinIterable<Int>(1, 2, 3)) {
        println("[MyKotlinIterable] The number is: $item")
    }

}