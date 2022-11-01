fun main() {
    val customerOne = Customer("Donn")
    println(customerOne)
    val chefDetails = Chef("pastries","ribs")
    println(chefDetails)
}

open class Customer(name: String) {



    protected var favoriteFood = "unknown"



}

class Chef(var culinary: String, favFood: String) : Customer(name="dill") {
    init {
        favoriteFood = favFood
    }
}