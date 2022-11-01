fun main() {
    val personOne = Person("john", 24)

}

/**
Person is the class name
firstName and age are the parameters
Init initializer block is the class body
 */
/*
class Person( firstName: String, age: Int) {
    init {
        println(firstName)
        println(age)
    }
}
*/

/*constructors initialize class properties
Primary constructor - concise way to initialize a class
Secondary constructor - allows you to put additional initialization logic
 */

class Person(var firstName: String? = null, var age: Int? = null) {
    var favoriteColor: String? = null
    var height: Int? = null

    init {
        val fName = firstName?.uppercase()
        println(fName)
        println(age)
    }

    companion object {
        var people = mutableListOf<Person>()
        fun createUsers(count: Int): List<Person> {
            for (i in 0..count) {
                people.add(Person("firstname $i", i))
            }
            return people
        }

        fun callMeByName(): String? {
            return "Your name is john"
        }
    }

    var fullName = "$firstName  $age"
        get() = "Full details : $field"
        set(value) {
            if (value.startsWith("john")) {
                field = "Johny Depp"
            } else {
                field = value
            }
        }

}




