fun main() {
    println(FavoriteFood.name)

    FavoriteFood.name = "watermelon"
    println(FavoriteFood.name)

    FavoriteFood.ingredients.add("salt")
    println(FavoriteFood.ingredients)
    println(FavoriteFood.numberOfIngredients())

    doStuff()
    println(FavoriteFood.name)
    println(FavoriteFood.ingredients.firstOrNull())
    println(FavoriteFood.numberOfIngredients())

    var a = A()
    println(a)
    Singleton.printName()
}

fun doStuff() {
    FavoriteFood.name = "pineapple"
    FavoriteFood.ingredients.clear()
}

object FavoriteFood {
    var name = "unknown"
    var ingredients = mutableListOf<String>()

    init {
        println("Singleton class invoked.")
        println(FavoriteFood.name)
    }

    fun numberOfIngredients(): Int {
        return ingredients.size
    }
}

//singleton object extending a class

open class A() {
    open fun printName() {
        println("I am in a private class")
    }

    init {
        println("I am in init of A")
    }
}

object Singleton : A() {
    init {
        println("Singleton class invoked.")
    }

    var variableName = "I am var"

    override fun printName() {
        println(variableName)
    }


}