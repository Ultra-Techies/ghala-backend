fun main() {
    val jack = Prefect("Jack")
    jack.displayJob("sweeping")
    jack.displayStudentId(1234)
}

abstract class Student(name: String) {
    init {
        println("My name is $name")
    }

    fun displayStudentId(id: Int) {
        println("Id number is $id")
    }
    abstract fun displayJob(job:String)
}

 class Prefect(name:String): Student(name){
     override fun displayJob(job: String) {
         println(job)
     }

 }