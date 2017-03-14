
package com.jdmuriel.euler.euler57

import com.jdmuriel.euler.utils.getDigitCount
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 12/03/2017
 * https://projecteuler.net/problem=57
 * Square root convergents
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - BigIntegers
 * OTHER COMMENTS:
 * -
 * 30230th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0

    var nextExpansion = BigFraction(BigInteger.ONE, BigInteger.ONE)
    val LIMIT = 1000
    (1..LIMIT).forEach {
        nextExpansion = getNextSqrtOf2Expansion(nextExpansion)
        //println (nextExpansion)
        if (nextExpansion.numerator.getDigitCount()>nextExpansion.denominator.getDigitCount()) {
            count++
        }
    }

    println ("Count: $count.")
}

/*
    1 + 1/2 = 3/2
    1 + 1/(1+3/2) = 1 + 1/(5/2) = 1 + 2/5 = 5/5 + 2/5 = 7/5
    1 + 1/(1+7/5) = 1 + 1/(12/5) = 1 + 5/12 = 12/12 + 5/12 = 17/12
    In each step n+1,
        denominator_n+1 = numerator_n+denominator_n
        numerator_n+1 = denominator_n + (numerator_n + denominator_n) = 2 * denominator_n + numerator_n
    Next step = 2*12 + 17 / 17+12 = 41/29
    Then, 2*29 + 41 / 29 + 41 = 99/70
*/
private data class BigFraction(val numerator: BigInteger, val denominator: BigInteger)

private fun getNextSqrtOf2Expansion (previous: BigFraction) : BigFraction {
    val newDenom = previous.numerator + previous.denominator
    val newNum = newDenom + previous.denominator
    return BigFraction(newNum, newDenom)
}