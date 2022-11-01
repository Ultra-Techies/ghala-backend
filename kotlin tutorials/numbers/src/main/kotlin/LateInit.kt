fun main() {
    MySchool().initializeClass()
}

class MySchool {
    //'lateinit' modifier is not allowed on properties of nullable types
    private lateinit var myClass: String

    //'lateinit' modifier is not allowed on properties of primitive type-compilation error
   /* private lateinit var myAge : Int*/

    fun initializeClass() {
        println("Has my class started? " + this::myClass.isInitialized)
        myClass = "Science"
        println("Has my class started? " + this::myClass.isInitialized)
    }
}

