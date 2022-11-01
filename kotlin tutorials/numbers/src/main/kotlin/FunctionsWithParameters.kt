fun main() {
    recordAge(12, "Donn")
    recordAge(4, "Dean")
    readBooK("Green eggs and hams", "Dr seus", "Dr lim")
}

fun recordAge(age: Int, name: String) {

    if (age > 8) {
        println("your age is $age and your name is $name")
    } else {
        println("$name you are not yet old at $age ")
    }

}

//Multiple Args with varargs
fun readBooK(title: String, vararg author: String) {
    println("$title- authors are:")
    author.forEach { println(it) }
}