// Python classes are open
open class StudentVersion2 {

    // Very awkward code to try to replicate Python version's equivalent
    var _name: String? = null
    var name: String?
        get() = this._name
        set(value) {
            this._name = "Comrade $value"
        }

}

fun main() {

    val student2 = StudentVersion2()
    student2.name = "Candy"
    println(student2.name)

}