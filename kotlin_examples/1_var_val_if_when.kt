/**
In-class Kotlin example code
This file covers val and var, and if and when 
*/
fun main() {
    val kotlin = "🙂" // val needs to be assigned and cannot be changed
    println("🙂")

	var myDog = "Paris"
    println(myDog)
    
    myDog = myDog + " pup" // myDog is a var so I can update it
    println(myDog)
    
    
    val numDogs: Int = 27 // I can explicitly say what the type of my variable is
    val numCats: Int = 7
    
    var totalAnimals: Int // if you give a var a type, you can postpone assignment (I don't recommend it though)
    
    println(numDogs)
    println(numCats)
    
    totalAnimals = numCats + numDogs
    println(totalAnimals)
    
    if (numDogs > numCats) {
        println("More dogs than cats")
    } else {
        println("Too many cats")
    }
        
    // when statement takes numDogs, and each line checks to see if the condition matches
    // either equal to the value or in a range of values    
    val dogMsg: String = when (numDogs) {
        0 -> "NO DOGS!!!!"
        in 1..39 ->  "Between 1 to 40 dogs"
        27 ->  "27 dogs"
        else ->  "Lotta dogs"
    }
    
    println(dogMsg)
}