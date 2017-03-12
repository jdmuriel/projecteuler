
package com.jdmuriel.euler.euler56

import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on xx/03/2017
 * https://projecteuler.net/problem=56
 * Powerful digit sum
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - BigInteger.pow
 * - CharSequence.fold to calculate sum of digits
 * OTHER COMMENTS:
 * -
 * 43283rd person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var max = 0
    for (i in 1..100)
        for (j in 1..100) {
            val power = i.toBigInteger().pow(j)
            val sumDigits = getSumOfDigits( power )
            if (sumDigits > max) {
                println ("New maximum: $i pow $j suma $sumDigits: $power")
                max = sumDigits
            }
        }

    println ("Max digits: $max")
}

fun getSumOfDigits (n: BigInteger) : Int {
    return n.toString().fold (0, {acc, c -> acc + (c-'0')})
}