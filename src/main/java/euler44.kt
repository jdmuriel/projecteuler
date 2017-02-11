
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 10/02/2017
 * https://projecteuler.net/problem=44
 * Pentagon numbers
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - TreeSet
 * - Triples
 * - Assignment to tuple
 * OTHER COMMENTS:
 * - Brute force solution, no time to detect condition to end search.
 * 41827th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private var pentagonals : SortedSet<Long> = TreeSet()

private fun calc(): Unit {

    var n = 1L
    var mindiff = Long.MAX_VALUE
    while (n<10000) {
        //Check if pentagonal(n) is sum of two pj, pk, such as pj-pk is also pentagonal
        val pn = getPentagonalNumber(n)
        println ("checking $n - $pn...")
        pentagonals.add (pn)
        val (found, pj, pk) = matchesCondition (pn)
        if (found) {
            val difference = Math.abs(pk-pj)
            println ("Found $pj, $pk, difference: $difference")
            if (difference < mindiff) {
                mindiff = difference
                println ("NEW RECORD")
            }
        }
        n++
    }

    println ("Difference: $mindiff")
}

fun matchesCondition (n: Long) : Triple<Boolean, Long, Long> {
    for (i in pentagonals) {
        if (n-i <=i ) return Triple (false, 0,0)
        if (pentagonals.contains (n-i) && pentagonals.contains (n-i-i))
            return Triple(true, i, n-i)
    }
    return Triple (false, 0, 0)
}

fun getPentagonalNumber (n: Long) : Long {
    return n * (3*n -1) / 2
}