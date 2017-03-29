
package com.jdmuriel.euler.euler65

import com.jdmuriel.euler.utils.Fraction
import com.jdmuriel.euler.utils.sumOfDigits
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 25/03/2017
 * https://projecteuler.net/problem=65
 * Convergents of e
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - IntArray, Array<T> {init}, Range.forEach
 * - Groovy Spock tests
 * OTHER COMMENTS:
 * - Easy to code, complex mathematics. Thanks to the awesome resource on Continued Fractions
 *      http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/cfINTRO.html
 * 22152nd person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val c = getConvergentsFromCF(getContinuedFractionTermsOfE(100))
    println ("100th convergent: ${c[99]}. Sum of digits: ${c[99].numerator.sumOfDigits()}")
}

/**
 * CF Term      2   1        2          1   1   4   1   1   6   1
 */
fun getContinuedFractionTermsOfE(termCount: Int): IntArray {
    val terms = IntArray(termCount, ::getTermOfE)
    return terms
}

fun getTermOfE(i: Int) : Int {
    return if (i.rem(3)==2) (i+1)/3*2 else if (i==0) 2 else 1
}

/**
 * CF Term      2   1        2          1   1   4   1   1   6   1
 * Num      1   a=2 1x2+1=3  2x3+2=8
 * Denom    0   1   1x1+0=1  2x1+1=3
 * Formula for the convergents of a Continued Fraction CF [a0; a1, a2, a3, etc.] is
 * Ci i=1..n is numi/denomi, with num0=1, num1=a, denom0=0, denom1=1
 * numi+1 = ai+1*numi+numi-1    denomi+1=ai+1*denomi + denomi-1
 */
fun getConvergentsFromCF(termsOfE: IntArray) : Array<Fraction<BigInteger>> {
    val nums = Array<BigInteger>(termsOfE.size + 1) { BigInteger.ZERO }
    val denoms = Array<BigInteger>(termsOfE.size + 1) { BigInteger.ZERO }
    nums[0] = BigInteger.ONE
    denoms[0] = BigInteger.ZERO
    nums [1] = termsOfE[0].toBigInteger()
    denoms[1] = BigInteger.ONE
    (2..termsOfE.size).forEach { i ->
        nums[i] = termsOfE[i-1].toBigInteger() * nums[i-1] + nums[i-2]
        denoms[i] = termsOfE[i-1].toBigInteger() * denoms[i-1] + denoms[i-2]
    }
    return Array(termsOfE.size) { Fraction (nums[it+1], denoms[it+1]) }
}
