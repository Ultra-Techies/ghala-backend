fun main() {
    dataManipulation()
}

fun dataManipulation() {
    var array = arrayOf(1, 2, 3, 4, 5)
    array.forEach { println(it) }
    val listItems = listOf(4, 5, 6, 7, 7)
    listItems.forEach { println(it) }

    val names = listOf("Donn", "Felker", "John", "Mary")
    val newList = names.filter { it.lowercase().contains("e") }
    println(newList)
}

