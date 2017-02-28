
import kotlin.system.measureTimeMillis


/**
 * Created by @jdmuriel on 28/02/2017
 * https://projecteuler.net/problem=52
 * Permuted multiples
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - CharArray.apply {sort}
 * - forEach, forEachIndexed, return@forEach
 * OTHER COMMENTS:
 * -
 * 48531 person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {

    var found = false
    var i = 1
    while (!found) {
        if (i%10000==0) println("$i...")
        var matches = 1
        (2..6).forEach { if (checkSameDigits (i,i*it)) matches++ else return@forEach }
        if (matches==6) {
            println ("Found: $i, ${i*2}, ${i*3}, ${i*4}, ${i*5}, ${i*6}")
            found = true
        }
        i++
    }
}

private fun checkSameDigits(i1: Int, i2: Int): Boolean {
    val c1 = i1.toString().toCharArray().apply { sort() }
    val c2 = i2.toString().toCharArray().apply { sort() }
    if (c1.size != c2.size) return false
    c1.forEachIndexed { i, c -> if (c2[i]!=c) return false }
    return true
}
