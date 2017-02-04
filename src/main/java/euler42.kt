
import com.google.common.io.Resources
import com.jdmuriel.euler.utils.getWordValue
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 03/02/2017
 * https://projecteuler.net/problem=42
 * Coded triangle numbers
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - String.split, replace
 * - Iterable.map, forEach
 * - Math.floor, sqrt
 * - Guava Resources
 * OTHER COMMENTS:
 * -
 * 55230th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private val FILE_WORDS = "p042_words.txt"

private fun calc(): Unit {

    var count = 0

    val file = Resources.toString(Resources.getResource(FILE_WORDS), Charsets.ISO_8859_1)
    val words = file.split(",").map { it.replace("\"", "")}
    words.forEach {
        if ( isTriangular (getWordValue(it)) ) {
            println ("Found: $it, value: ${getWordValue(it)}")
            count++
        }
    }
    println ("Count: $count.")
}

/**
 * given t = n*(n+1)/2, with n integer
 * n^2 + n - 2t = 0 has solutions (-1+-sqrt(1+8*y))/2
 * t is triangular iif sqrt(1+8*t) is integer and odd
 */
fun isTriangular (number: Int) : Boolean {
    val aux = Math.sqrt(1 + 8*number.toDouble())
    return (  Math.floor(aux)==aux      //Check aux is integer
            && aux.toInt() % 2 == 1     // and even
            )
}
