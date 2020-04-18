open class Person(val name: String) {
    open fun introduceSelf() {
        println("My name is $name.")
    }
}

open class Student(name: String, val year: Int) : Person(name)

class PoliteStudent(name: String, year: Int) : Student(name, year) {
    override fun introduceSelf() {
        super.introduceSelf()
        println("It's a pleasure to meet you.")
    }
}

interface Animal {
    fun makeSound()
}

class LabRat(val sound: String) : Animal {
    override fun makeSound() {
        println(sound)
    }
}


fun main() {

    val student1 = Student("Ashley", 6)
    println(student1.name) // output: 'Ashley'
    println(student1.year) // output: 6
    // call the inherited method
    student1.introduceSelf() // output: 'My name is Ashley.'

    val student2 = PoliteStudent("Bob", 5)
    // call the overridden method
    student2.introduceSelf() // output: 'My name is Bob.\nIt's a pleasure to meet you.'

    val labRat = LabRat("Squeak!")
    // call the implemented method
    labRat.makeSound() // output: Squeak!

}