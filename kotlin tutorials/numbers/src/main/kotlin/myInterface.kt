fun main(){
MyInterfaceImpl()
}
interface MyInterface {
    val test: String //abstract property
    fun foo(): String
    fun hello() {
        "Hello There"
    } // function with default implementation
}

class MyInterfaceImpl() : MyInterface{
    override val test: String
        get() = "test one"

    override fun foo(): String {
       return "lol"
    }

}