fun main() {

    val girl = Girl()
/*    girl.age = 32
    println("My fake age is ${girl.age}.")*/
     girl.displayAge(31)
}


open class Log {
    var data: String = ""
    var numberOfData = 0

    constructor(_data: String) {

    }

    constructor(_data: String, _numberOfData: Int) {
        data = _data
        numberOfData = _numberOfData
        println("$data: $numberOfData times")
    }
}

class AuthLog : Log {
    constructor(_data: String) : this("From AuthLog -> + $_data", 10) {
    }

    constructor(_data: String, _numberOfData: Int) : super(_data, _numberOfData) {
    }
}
//overriding member functions
/*
open class Persons() {
    open fun displayAge(age: Int) {
        println("My age is $age.")
    }
}

class Girl:Persons(){
    override fun displayAge(age: Int) {
        println("My fake age is ${age - 5}.")
    }
}*/

//overriding properties of base class
/*open class Persons() {
    open var age: Int = 0
        get() = field
        set(value) {
            field = value
        }
}

class Girl : Persons() {
    override var age: Int = 0
        get() = field
        set(value) {
            field = value - 5
        }
}*/

//calling member functions and  properties of base class using the keyword super

open class Persons() {
    open fun displayAge(age: Int) {
        println("My actual age is $age.")
    }
}

class Girl:Persons(){
    override fun displayAge(age: Int) {
        super.displayAge(age)
        println("My fake age is ${age - 5}.")
    }
}
