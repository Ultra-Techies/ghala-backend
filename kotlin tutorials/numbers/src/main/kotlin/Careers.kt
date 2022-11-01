fun main() {
    val p = People("swahili", "fast", "everything")
    val m = MathTeacher("english", "slowly", "githeri", "english")
  
}

open class People(talk: String, walk: String, eat: String) {
    init {
        println("I can talk $talk")
        println("I can walk $walk")
        println("I can eat $eat")
    }
}

class MathTeacher(talk: String, walk: String, eat: String, highschoolTeacher: String) : People(talk, walk, eat) {
    init {
        println("I teach $highschoolTeacher")
    }
}

class Footballer(talk: String, walk: String, eat: String, dribble: Boolean) : People(talk, walk, eat) {
    init {
        println("I can dribble $dribble")
    }
}
