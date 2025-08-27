/**
 * CS 3170
 * In-class Kotlin example code
 * 8/27/2025
 * This file has demos of some string operations, for loops, lists, and null checking
 */
fun main() {
	// String examples
    val goodNews: String = "Good news, everybody!"
    val moreString = goodNews + "!!!"
    println("Professor Farnsworth says: \"${goodNews}\" when he has bad news")
    val len = goodNews.length
    println("His statement has ${goodNews.length} characters in it")
    
    val numDogs = 12
    val numCats = 4
    println("There are $numDogs dogs and $numCats cats with ${numDogs + numCats} total pets")
    
    // for loops over ranges
    for (i in 0..4) {
        print("$i ")
    }
    println("backwards:")
    for (i in 4 downTo 0) {
        print("$i ")
    }
    println()
    for (i in 0..99 step 11) {
        print("$i " )
    }
    
    // Lists
    // if insts were not a mutable list, we couldn't add, modify, or remove elemets
    //indexes:					0		  1		   2		3		
    val insts = mutableListOf("guitar", "bass", "drums", "mellotron")
    
    println(insts)
    println(insts.get(2))
    
    insts.set(3, "synth") // change element at index 3 to "synth"
    insts.add("piano") // add "piano" to the end of the list
    insts.add(2, "banjo") // insert "banjo" at index 2, pushing everthing down 1
    insts.remove("guitar") // delete "guitar" from the list
    insts.removeAt(2) // remove the element at index 2
    println(insts)
    
    val moreInsts = mutableListOf("triangle", "bongo", "tambourine")
    val allInsts = insts + moreInsts // create a new list combining elements from both
    
    print(allInsts)
    
    // null checking
    var numMovies: Int? = null // a regular Int cannot be null, we need Int?
    println(numMovies)
    
    // we can do the traditional null check
    // if numMovies is null, if we try to access the inc() method, we would get a null pointer exception
    if (numMovies != null) {
        numMovies = numMovies.inc() 
    } {
        numMovies = 0
    }
    
    // using ? before .inc() will only call the method if numMovies is not null
    // the ?: will evaluate the right hand side if the left hand side is null
    numMovies = numMovies?.inc() ?: 0
    
    println(numMovies)
}