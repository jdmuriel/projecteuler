@file:Suppress("LoopToCallChain")

package com.jdmuriel.euler.euler55

import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 11/03/2017
 * https://projecteuler.net/problem=55
 * Lychrel Numbers
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Spock tests
 * - BigInteger
 * - String.toCharArray to check palindromes
 * OTHER COMMENTS:
 * - Surprisingly high number of Lychrel numbers.
 * 39880th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0

    val MAX_NUMBER = 10000
    for (i in 1..MAX_NUMBER) {
        if (isLychrelUnderTenThousand(i)) {
            count++
            println ("Found $count Lychrel number: $i")
        }
    }

    println ("Count: $count")
}

//If 50 iterations do not produce a palindrome, it is a Lychrel number
private fun isLychrelUnderTenThousand (n: Int) : Boolean {
    val MAX_ITERATIONS = 50
    var nAux = n.toBigInteger()
    //println("Checking n...")
    for (i in 1..MAX_ITERATIONS) {
        //println(nAux)
        nAux = getNextReverseAndAdd(nAux)
        if (isPalindrome(nAux))
            return false
    }
    return true
}

fun getNextReverseAndAdd (n:BigInteger) : BigInteger {
    return n + getReverse(n)
}

fun getReverse (n:BigInteger) : BigInteger {
    val s = n.toString().toCharArray()
    var reversed = BigInteger.ZERO
    for (i in s.size-1 downTo 0) {
        reversed = reversed * 10.toBigInteger() + (s[i]-'0').toBigInteger()
    }
    return reversed
}

fun isPalindrome (n: BigInteger) : Boolean {
    val s = n.toString().toCharArray()
    for (i in 0..(s.size-1)/2) {
        if (s[i]!=s[s.size-i-1])
            return false
    }
    return true
}