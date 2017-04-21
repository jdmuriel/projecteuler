
package com.jdmuriel.euler.euler70

import com.jdmuriel.euler.euler69.TotientSieve
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 20/04/2017
 * https://projecteuler.net/problem=70
 * Totient permutation
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - String.toCharArray; CharArray.sortedArray, joinToString; Iterable.forEachIndexed
 * OTHER COMMENTS:
 * -
 * 16378 person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0

    val sieve = TotientSieve(10_000_000)
    //val sieve = TotientSieve(90_000)
    var min_ratio = 10_000_000.0
    var min_n = 0
    sieve.forEachIndexed { i, fi ->
        if (i>0) {
            if (isPermutation((i + 1).toLong(), fi)) {
                count++
                val ratio = (i + 1).toDouble() / fi
                if (ratio < min_ratio) {
                    min_ratio = ratio
                    min_n = i + 1
                    println("Found min ratio: n=$min_n, fi=$fi, ratio=$min_ratio")
                }
            }
        }
    }

    println ("Count: $count. Found min ratio: n=$min_n, ratio=$min_ratio")
}

private fun isPermutation (n1: Long, n2: Long) : Boolean {
    val s1 = n1.toString()
    val s2 = n2.toString()
    if (s1.length==s2.length) {
        //same length, test if they are permutations
        return ( s1.toCharArray().sortedArray().joinToString("") == (s2.toCharArray().sortedArray().joinToString("")))
    }
    return false
}