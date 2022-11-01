fun main() {
    performLetOperation()
}

class Person2() {
    var name: String? = "Abcd"
    var contactNumber: String = "1234567890"
    var address: String = "xyz"
    fun displayInfo() = print(
        "\n Name: $name\n " +
                "Contact Number: $contactNumber\n " +
                "Address: $address"
    )

}

private fun performLetOperation() {
    val person = Person2().let {
        "Let - The name of the Person is: ${it.name}"
    }
    val person2 = Person2().run {
        "Run - The name of the Person is: $name"
    }
    val name = Person2().name?.run {
        "Run with nullable- The name of the Person is: $this"
    }
    val person3= with(Person2()) {
        return@with "With - The name of the Person is: ${this.name}"
    }
    println(person)
    println(name)
    println(person)
    println(person2)
    println(person3)
}