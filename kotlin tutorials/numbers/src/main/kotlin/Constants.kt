fun main() {

    val newUser = User(12, "John")
    User.MAX_AGE
    User.MIN_AGE
}

class User(var age: Int?, var name: String?) {

    companion object {
        val MAX_AGE = 18
        val MIN_AGE = 10
    }


    private fun fullDetails(): String = "$name -$age"

    override fun toString(): String {
        return fullDetails()
    }
}