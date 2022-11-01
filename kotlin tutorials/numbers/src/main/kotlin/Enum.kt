fun main() {
    println(AccountTypes.GOLD)
    println(AccountTypes.GOLD.discountTypes)

    val accountTypeFromBackend = "gold"
    val account = AccountTypes.valueOf(accountTypeFromBackend.uppercase())
    println(account)

}

enum class AccountTypes(val discountTypes: Int) {
    BRONZE(10),
    SILVER(20),
    GOLD(30),
    PLATINUM(40)
}

enum class Gender {
    BOY {
        override fun determinePronoun(): String {
            TODO("Not yet implemented")
        }
    },
    GIRL {
        override fun determinePronoun(): String {
            TODO("Not yet implemented")
        }
    },
    OTHER {
        override fun determinePronoun(): String {
            TODO("Not yet implemented")
        }
    };

    abstract fun determinePronoun(): String
}