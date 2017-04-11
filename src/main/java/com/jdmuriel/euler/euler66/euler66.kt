@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package com.jdmuriel.euler.euler66

import com.jdmuriel.euler.utils.Fraction
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.coroutines.experimental.buildSequence
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 29/03/2017
 * https://projecteuler.net/problem=66
 * Diophantine equation
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Kotlin coroutines with yield and BuildSequence (using recently released Kotlin 1.1,
 *      which still flags these function as experimental
 * - Groovy tests do not work with coroutines :-(
 * OTHER COMMENTS:
 * - Great problem! Tried solving it by brute force, but time quickly grow to iterate to billions
 *      Wikipedia had a wonderful explanation of Pell equation and the application of continuous function
 *      Wonderful how two apparently unrelated mathematical constructs come together!
 * 14210th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

fun calc() {
    var maxX = BigInteger.ZERO
    var maxX_D = 0

    /*Tests
    val l1 = getSquareRootContinuousFraction(2).take(5).toList()
    println (l1)
    val lc1 = getConvergentsFromCF(l1.map {it.toInt()}.toIntArray())
    println (lc1.toList())
    val lc2 = getConvergents(sequenceOf(l1[0], l1[1], l1[2], l1[3], l1[4]))
    println (lc2.toList())
    */

    (2..1000).forEach { D ->
        if (!isSquare(D)) {
            val f = getFundamenalSolutionPellEquation(D)
            if (f.numerator > maxX) {
                maxX = f.numerator
                maxX_D = D
                println("New max x found: $maxX for D=$maxX_D")
                println(" D=$D, x=${f.numerator}, ${f.numerator}^2 - $D*${f.denominator}^2=1")
            }
        }
    }

    println ("Max x found: $maxX for D=$maxX_D")
}

private fun isSquare (D: Int) : Boolean {
    val root = Math.sqrt(D.toDouble()).toInt()
    return (root * root == D)
}

/**
 * Let hi/ki denote the sequence of convergents to the regular continued fraction for sqrt(D).
 * This sequence is unique. Then the pair (x1,y1) solving Pell's equation x^2 -Dy^2 = 1
 * and minimizing x satisfies x1 = hi and y1 = ki for some i.
 * This pair is called the fundamental solution.
 * Thus, the fundamental solution may be found by performing the continued fraction expansion
 * and testing each successive convergent until a solution to Pell's equation is found.
 * https://en.wikipedia.org/wiki/Pell%27s_equation
 */
fun getFundamenalSolutionPellEquation(D : Int) : Fraction<BigInteger> {
    for (f in getSquareRootConvergents(D)) {
        if (f.numerator*f.numerator - D.toBigInteger() *f.denominator*f.denominator == BigInteger.ONE) {
            return f
        }
    }
    //It should never get here
    return Fraction(BigInteger.ONE, BigInteger.ONE)
}

fun getConvergents (continuousFraction: Sequence<BigInteger>) : Sequence<Fraction<BigInteger>> {
    var numBeforePrevious = BigInteger.ONE
    var denomBeforePrevious = BigInteger.ZERO
    var numPrevious = BigInteger.ZERO   //Initial invalid value
    var denomPrevious = BigInteger.ZERO //Initial invalid value
    return buildSequence {
        continuousFraction.forEachIndexed { index, term ->
            if (index == 0) {
                numPrevious = term
                denomPrevious = BigInteger.ONE
                yield(Fraction(numPrevious, denomPrevious))
            } else {
                val num = term * numPrevious + numBeforePrevious
                val denom = term * denomPrevious + denomBeforePrevious
                numBeforePrevious = numPrevious
                denomBeforePrevious = denomPrevious
                numPrevious = num
                denomPrevious = denom
                yield (Fraction(num, denom))
            }
        }
    }
}

fun getSquareRootConvergents (D: Int) : Sequence<Fraction<BigInteger>> {
    return getConvergents (getSquareRootContinuousFraction(D))
}

//https://en.wikipedia.org/wiki/Methods_of_computing_square_roots
fun getSquareRootContinuousFraction(D : Int) : Sequence<BigInteger> {
    return buildSequence {
        val m0 = BigInteger.ZERO
        val d0 = BigInteger.ONE
        val a0 = Math.floor(Math.sqrt(D.toDouble())).toBigInteger()
        var m_previous = m0
        var d_previous = d0
        var a_previous = a0
        yield(a0)
        while (true) {
            val m = d_previous * a_previous - m_previous
            val d = (D.toBigInteger() - m*m)/d_previous
            val a = Math.floor((a0 + m).toDouble()/d.toDouble()).toBigInteger()
            m_previous = m
            d_previous = d
            a_previous = a
            yield (a)
        }
    }
}

/* Brute force initial attempt
private fun calc2(): Unit {
    var maxX = 0L
    var maxX_D = 0
    (2..1000).filter {
        val r = Math.sqrt(it.toDouble())
        r != Math.floor(r)
    }.forEach { D ->
        var found = false
        var y = 1L
        var x = BigInteger.ZERO
        //Search ys solving the equation x^2-D*y^2=1
        while (!found) {
            y++
            val xsquared = 1.0 + D*y*y
            //Possible integer y
            x = Math.sqrt(xsquared).toBigInteger()
            found = x*x==1+D*y*y
        }
        println (" D=$D, x=${x.toLong()}, $x^2 - $D*$y^2=1")
        if (x>maxX) {
            maxX = x.toLong()
            maxX_D = D
            println ("New max x found: $maxX for D=$maxX_D")
        }
        if (x>1905497077L) {
            println ("Possible D: $D")
        }
    }

    println ("Max x found: $maxX for D=$maxX_D")

    findByX(maxX_D) //returns x=1905497077L instead of 2178548422**

}


private fun findByX(D: Int): Long {
    var found = false
    var x = 1L
    var y = 0.0
    while (!found) {
        x++
        val ysquared = (x*x -1.0)/D
        if (Math.floor(ysquared)==ysquared) {
            //Possible integer y
            y = Math.sqrt(ysquared)
            found = (Math.floor(y)==y)
        }
    }
    println (" D=$D, x=$x, $x^2 - $D*$y^2=1")
    return x
}
*/
