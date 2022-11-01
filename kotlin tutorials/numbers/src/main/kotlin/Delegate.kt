fun main() {
    val b = BaseImplementation(10)
    Delegate(b).println() // prints 10 :: accessing the println() method
    Delegate(b).printMessageLn()

    val newMessages = MessagesImpl("hometime")
    MessagesDelegate(newMessages).message()
    MessagesDelegate(newMessages).printNewMessage()

    val totalAmount = PaymentsImplementation(2000)
    PaymentsDelegate(totalAmount).getPayments()

    val measurements = Circle(100)
    WindowDelegate(measurements).calculateArea()
}

interface Base {
    fun println()//abstract method
    fun printMessageLn()//abstract method
}

class BaseImplementation(val x: Int) : Base {
    override fun println() {
        println("print 1 $x") //implementation of the method
    }

    override fun printMessageLn() {
        println("print 2 $x") //implementation of the method
    }

}

class Delegate(b: Base) : Base by b { // delegating the public method on the object b
    override fun println() {
        print("abc")
    }
}

interface Messages {
    fun message()
    fun printNewMessage()
}

class MessagesImpl(val a: String) : Messages {
    override fun message() {
        println(a)
    }

    override fun printNewMessage() {
        println(a)
    }

}

class MessagesDelegate(c: Messages) : Messages by c {
    override fun message() {
        println("delegates by delegates")
    }
}

interface Payments {
    fun getPayments()
}

class PaymentsImplementation(val amount: Int) : Payments {
    override fun getPayments() {
        println(amount)
    }
}

class PaymentsDelegate(paymentsDelegate: Payments) : Payments by paymentsDelegate {
    override fun getPayments() {
        println("total amount is 1000")
    }
}

interface Shape {
    fun calculateArea()
}

class Circle(private val area: Int) : Shape {
    override fun calculateArea() {
        println(area)
    }

}
class WindowDelegate(private val windowDelegate: Shape): Shape by windowDelegate{
    override fun calculateArea() {
        println("area is 1000 mtrs")
    }
}