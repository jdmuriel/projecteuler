
package com.jdmuriel.euler.euler63

import com.jdmuriel.euler.utils.getDigitCount
import com.jdmuriel.euler.utils.toBigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 19/03/2017
 * https://projecteuler.net/problem=63
 * Powerful digit counts
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - BigIntegers
 * OTHER COMMENTS:
 * -
 * 31703rd person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}


/** Analysis
 * 10**n always has n+1 digits, and every i>10 will be even bigger so will never match the condition.
 * 1**n has 1 digit, so it only matches the condition for n=1
 * 2**1 has 1 digit (matches), 2**2 has 1 digit (unmatch), any further 2**n will have always less than n digits.
 * So for i<n, once we find an n for which i**n has less than n digits, we can stop.
 */

private fun calc(): Unit {
    var count = 0

    (1..9).forEach { i ->
        var n = 0
        var keepChecking = true
        while (keepChecking) {
            n++
            val power = i.toBigInteger().pow(n)
            val digits = power.getDigitCount()
            if (digits<n) {
                //No more matches
                keepChecking = false
            } else if (digits == n) {
                count++
                println ("$i**$n=$power, whith $digits digits")
            } else {
                //will never stop here
                println ("Strange case: $i**$n=$power, whith $digits digits")
            }
        }
    }

    println ("Count: $count")
}
