fun main() {
    var poem=MyPoem();
    println(poem.poemTitle);
    println("My Second Poem is Polly the sheep--"+poem.poemTitle);
}

class MyPoem {
    // no compilation error
    val poemTitle: String? by lazy {
        println("Billy The Goat")
        "Verse One"
    }

    //no compilation error
    val myAge by lazy {
        100
    }
}



