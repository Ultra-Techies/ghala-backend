fun main(){
val wd = WebDeveloper("Jon",23,3000)
    wd.website()
    val ad = androidDeveloper("Gaurav", 24,12000)
    ad.android()
    val iosd = iosDeveloper("Praveen", 26,15000)
    iosd.iosapp()
    val ceo = CEO("BILL",35,6000.00)
}
open class Employee (name:String,age:Int,salary:Int){
    init {
        println("my name is $name my age is $age and my salary is $salary")
    }
}
class WebDeveloper(name: String,age: Int,salary : Int):Employee(name,age,salary){
    fun website() {
        println("I am website developer")
        println()
    }

}
class androidDeveloper( name: String,age: Int,salary : Int): Employee(name, age,salary) {
    fun android() {
        println("I am android app developer")
        println()
    }
}
//derived class
class iosDeveloper( name: String,age: Int,salary : Int): Employee(name, age,salary) {
    fun iosapp() {
        println("I am iOS app developer")
        println()
    }

}
open class Employees (name:String,age:Int){
    init {
        println("my name is $name my age is $age ")
    }
}
// derived class
class CEO( name: String, age: Int, salary: Double): Employees(name,age) {
    init {
        println("Salary per annum is $salary crore rupees")
    }  }