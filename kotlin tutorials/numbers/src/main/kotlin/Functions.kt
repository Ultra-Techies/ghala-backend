fun main(){
    //by default return type of function in kotlin is a unit
    val x = sayHi()
    println(x)

    val b = sayHello()
    println(b)

    val c = sayAge()
    println(c)

    //Single Line Expressions
    val age = 12

    fun doWork() = if (age>12) println("Eligible") else println("Not eligible")

    doWork()
}

fun sayHi(){
    println("Hello there")
}

fun sayHello(): String{
    return "hello friend"
}

fun sayAge(): Int{
    return 32
}