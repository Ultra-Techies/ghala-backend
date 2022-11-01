fun main() {
    printUserActions()
}

enum class UserActions(val actionName: String) {
    PAID("paid"),
    COMPLETED("completed")
}

fun printUserActions(){
    for (action in UserActions.values()){
        println("This is user action ${action.ordinal} ----> ${action.name}")
    }
    println(UserActions.valueOf("PAID"))
}
