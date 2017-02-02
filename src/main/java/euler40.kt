
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 01/02/2017
 * https://projecteuler.net/problem=40
 * Champernowne's constant
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Lists, mutableListOf, toMutableList, removeAt
 * - Range (1..n).forEach
 * - List.reduce
 * OTHER COMMENTS:
 * -
 * 58860th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc(): Unit {

    var aux = 1
    val positions = mutableListOf<Int>()
    (1..7).forEach { positions.add(aux); aux*=10 }
    println ("Positions: $positions")

    val digits = findChampernownePositions(positions)
    println ("Digits found: $digits")

    val product = digits.reduce { i, j -> i * j }
    println ("Product: $product")
}

//returns the digits in the decimal positions given in l
fun findChampernownePositions(l: List<Int>) : List<Int> {
    var currentNumber = 0           //Iterator from 1 up to needed
    var currentNumberDigits = ""    //The string with the i value we are adding
    var currentNumberPos = 0        //The position in the number we are checking
    var currentPosition= 1          //The decimal digit position we are currently on
    val positions = l.toMutableList()
    val digitsFound = mutableListOf<Int>()
    while (positions.isNotEmpty()){
        //If we need a new number, generate it
        if (currentNumberPos >= currentNumberDigits.length) {
            //Get next number
            currentNumber++
            currentNumberDigits = currentNumber.toString()
            currentNumberPos = 0    //Check first digit
        }
        //Check if current position is one of asked
        if (currentPosition==positions[0]) {
            val digit = currentNumberDigits[currentNumberPos]-'0'
            println ("Position $currentPosition found when adding digit $digit (position $currentNumberPos of integer $currentNumber)")
            //This is the next position we want
            positions.removeAt(0)
            digitsFound.add (digit)
        }
        currentPosition++
        currentNumberPos++
    }
    return digitsFound
}