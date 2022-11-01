fun main() {
    var br = Vehicle()
    br.brand = "Fiat"
    br.info()

    val sw = br.SteeringWheel()
    sw.info()

    val tw = Vehicle.Transmission()
    tw.shift()
}

class Vehicle() {
    var brand = "unknown"

    fun info() {
        println(brand)
    }

    //inner class
    inner class SteeringWheel {
        var name = "steering wheel"
        fun info() {
            println("$name and brand is $brand")
        }
    }

    //nested class
    class Transmission {
        var type = "Automatic"

        fun shift() = println(type)
    }

}