fun main() {
    var fullName = "Don Mon"
    println(fullName.length)
     var fullNameCharArray = fullName.toCharArray()[0]
    println(fullNameCharArray)
    //In the following example, we take a string and convert this to an array of characters using String.toCharArray()
    // method.
    /*for (x in fullNameCharArray){
        println(x)
    }*/

    val age = 32

    println("Hello $fullName and your age is $age and your name is ${fullName.length} characters long")
}
