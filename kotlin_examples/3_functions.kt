fun printHello(name: String) : String {
	return "Hello $name"
}

fun getForecast(day: String, temp: Int): String {
    return "Today is $day and the temperature is $temp degrees"
}

fun printSpeed(modeOfTransportation: String, speed: String = "fast") {
    println("You are $modeOfTransportation $speed")
}

fun getWaterQuality(dirtLevel: Int, filterFunc: (Int) -> Int ): String {
    val quality = filterFunc(dirtLevel)
    return "The water quality is $quality units good"
}

fun main() {
//     val myName = "James"
// 	val returnVal = printHello(myName)
    
    //val returnVal = getForecast(temp = 70, day = "Monday")
    
    //println(returnVal)package
    
//     printSpeed("driving")
//     printSpeed("walking", "slow")
    
    val dirtLevel: Int = 20
    // name			type			value
    val filterFunc: (Int) -> Int = {level -> (level) / 2}
    val result = filterFunc(dirtLevel)
    println(result)
    
    val result2 = getWaterQuality(dirtLevel, filterFunc)
    println(result2)
    
    val result3 = getWaterQuality(5, {level -> level * 2})
    println(result3)
}