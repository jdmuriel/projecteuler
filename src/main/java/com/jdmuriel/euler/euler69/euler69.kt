
package com.jdmuriel.euler.euler69

import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 13/04/2017
 * https://projecteuler.net/problem=69
 * Totient maximum
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - LongArray, LongArray.Iterator, toList, forEachIndexed
 * OTHER COMMENTS:
 * -
 * 25145th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val SIZE = 1_000_000
    val totientSieve = TotientSieve(SIZE)
    //println (totientSieve.map {it})
    //println (totientSieve.mapIndexed {i , fi -> if (i==0) 1 else (i.toDouble()+1)/fi })

    var max = 0.0
    var max_i = 0
    totientSieve.forEachIndexed { i, fi ->
        val ratio = if (i==0) 1.0 else (i.toDouble()+1)/fi
        if (ratio>max) {
            max = ratio
            max_i = i+1
            println ("New max ratio. i = ${i+1}, fi=$fi, ratio = $ratio")
        }
    }
    println ("Solution: $max_i")

}

/**
 *      formula for calculating fi(n) is n * product (1-1/p),  for each p prime divisor of n
 *      https://en.wikipedia.org/wiki/Euler%27s_totient_function
 *      Initiate sieve[n-1] with n, count of numbers less of n
 *      Iterate i = 2..n/2
 *          If sieve[i-1]==n, i.e. i is prime
 *              sieve[i-1]=n-1
    *          Iterate j = i*2; j<=n; j+=i
 *                  sieve[j-1] = sieve[j-1]*(1-1/i)
 */
private class TotientSieve (val maxn: Int) : Iterable<Long> {

    private val sieve = LongArray(maxn) { it+1L }

    init {
        fillTotientSieve()
    }

    private fun fillTotientSieve() {
        //println ("Initial sieve: ${sieve.toList()}")
        for (i in 2..maxn) {
            if (sieve[i-1]==i.toLong()) {
                sieve[i-1]--
                //i is prime, traverse multiples multiplying by (i-1)/i
                for (j in (i*2..maxn).step(i)) {
                    sieve[j-1] *= (i-1L)
                    sieve[j-1] /= i.toLong()
                }
                //println ("Sieve after processing multiples of $i: ${sieve.toList()}")
            }
        }
    }

    override fun iterator(): Iterator<Long> {
        return sieve.iterator()
    }

}
