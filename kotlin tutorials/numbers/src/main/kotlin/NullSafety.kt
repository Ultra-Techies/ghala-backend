fun main() {
    // variable is declared as nullable
    var lesson: String? = "Tutorial"
    if (lesson != null) {
        println("nice tutorial")
    } else {
        println("lesson not found")
    }
    //returns lesson string value in lowercase if string is not null, or null if string is empty
    println(lesson?.lowercase())

    var lessonTwo: String? = null

    //Executes a lambda on non-null objects
    //prints all except null
    var tutorialList: List<String?> = listOf("Kotlin", "Java", "JavaScript", null, "FLUTTER")
    for (tutorial in tutorialList) {
        tutorial?.let {
            println(it.lowercase())
        }
    }


    var newLesson = lesson ?: "Unknown"
    println(newLesson)
}
