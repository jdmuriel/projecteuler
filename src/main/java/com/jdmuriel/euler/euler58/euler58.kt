
package com.jdmuriel.euler.euler58

import com.jdmuriel.euler.PrimeArraySieve
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 14/03/2017
 * https://projecteuler.net/problem=58
 * Spiral primes
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - float, listOf, mutableListOf, simple arithmetic
 * OTHER COMMENTS:
 * - A one thousand million sieve needed to solve this! Would have thought that impossible to calculate in my PC
 * 29299th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val LIMIT = 1000000000
    val sieve = PrimeArraySieve(LIMIT)
    var side = 1
    var primeCount = 0
    var totalCount = 1
    while (true) {
        side+=2
        val newDiagonals = getDiagonalNumbers(side)
        println("$newDiagonals")
        newDiagonals.forEach {
            totalCount++
            if (sieve.isPrime(it)) {
                primeCount++
            }
        }
        val f = primeCount.toFloat()/totalCount * 100
        println ("square size $side: $primeCount primes of $totalCount numbers in diagonal: $f%")
        if (f<10 || newDiagonals[0]>LIMIT)
            return
    }
}

private fun getDiagonalNumbers (squareSide: Int) : List<Int> {
    if (squareSide==1) {
        return listOf(1)
    } else {
        //lower right is side*side
        var valueToAdd = squareSide*squareSide
        val list = mutableListOf(valueToAdd)
        //the other three are generated substracting (squareSide-1)*n
        for(i in 1..3) {
            valueToAdd -= squareSide-1
            list.add (valueToAdd)
        }
        return list
    }
}