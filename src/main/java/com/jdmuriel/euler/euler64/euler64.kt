
package com.jdmuriel.euler.euler64

import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 21/03/2017
 * https://projecteuler.net/problem=64
 * Odd period square roots
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - data class
 * - LinkedHashMap
 * OTHER COMMENTS:
 * -
 * 15996th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0

    val LIMIT = 10000
    (1..LIMIT).forEach {
        val fsr = FractionalSquareRoots(it)
        fsr.calc()
        val ai = fsr.ai.values.map { it.x }
        println ("Continuous fraction of sqrt $it: ${ai}")
        //println ("Fractional square roots for sqrt($it): ${fsr.x0},(${fsr.ai.map {it.value.x}})")
        if (fsr.ai.size % 2==1) {
            //odd
            count++
        }
    }
    println ("Number of odd periods: $count")
}

/**
 * For sqrt(23)
 * a0 : 1/(sqrt(23) -4) = (sqrt(23)+4)/(23-16) = (sqrt(23)+4)/7, which is 1.xxx, so is (7/7) +sqrt(23)+4-7/7 = 1 + (sqrt(23)-3)/7
 * a1 : 7/(sqrt(23)-3 = 7*(sqrt(23)+3)/23-9 = 7*(sqrt(23)+3)/14 = (sqrt(23)+3)/2, which is 3.89..., so is 3*2/2 + ((sqrt(23)+3-6)/2 = (sqrt(23)-3)/2
 * a2 : 2/(sqrt(23)-3) = 2*(sqrt(23)+3)/(23-9) = 2*(sqrt(23)+3)/14 = (sqrt(23)+3)/7 = 1.113... = 1*7/7 + (sqrt(23)+3-7)/7 = 1 + (sqrt(23)-4)/7
 * a3 : 7/(sqrt(23)-4) = 7*(sqrt(23)+4)/(23-16) = 7*(sqrt(23)+4)/7 = (sqrt(23)+4) = 8.79... = 8*1/1 + (sqrt(23)+4-8) = 8 + (sqrt(23)-4)
 * a4 : 1/(sqrt(23-4) cycle.
 * Inputs a_i = num_i/(sqrt(n)-b_i) = num_i * (sqrt(n)+b_i)/(n-b_i**2)
 * In the example, (n-b_i**2) is always a multiple of num_i
 * given f_i = (n-b_i**2)/num_i
 * a_i = (sqrt(n)+b_i)/f_i
 * given x_i+1 = floor(a_i)
 * a_i = floor(a_i)*f_i/f_i + (sqrt(n)+b_i-floor(a_i)*f_i)/f_i
 * arest_i = (sqrt(n)+b_i-floor(a_i)*f_i)/f_i
 * a_i+1 = 1/arest_i = f_i / (sqrt(n)+b_i-floor(a_i)*f_i)
 *
 * So given a_i = num_i/(sqrt(n)-b_i)
 * x_i+1 = floor (num_i/sqrt(n)-b_i)
 * a_i+1 = num_i+1/(sqrt(n)-b_i+1)
 *  where num_i+1 = (n-b_i**2)/num_i, b_i+1 = x_i+1*num_i+1-b_i
 *
 * Let's check
 * a0 : 1/(sqrt(23)-4), num0 = 1, b0 = 4
 * x1 = floor (1.25...) = 1
 * num1 = (23-b0**2)/num0 = (23-4**2)/1 = 7
 * b1 = x1*num1-b0 = 1*7-4 = 3
 * a1 = 7/(sqrt(23)-3)
 * x2 = floor(a1) = 3
 * num2 = (23-b1**2)/num1 = 2
 * b2 = x2*num2-b1 = 3*2-3 = 3
 * a2 = 2/(sqrt(23)-3
 * x3 = floor(a29 = 1
 * num3 = (23-b2**2)/num2 = (23-9)/2 = 7
 * b3 = x3*num3-b2 = 1*7-3 = 4
 * a3 = 7/(sqrt(23)-4)
 *
 * So given a_i = num_i/(sqrt(n)-b_i)
 * x_i+1 = floor (num_i/sqrt(n)-b_i)
 * a_i+1 = num_i+1/(sqrt(n)-b_i+1)
 *  where num_i+1 = (n-b_i**2)/num_i, b_i+1 = x_i+1*num_i+1-b_i
 *
 * For sqrt(2)
 * sqrt(2) = 1.41
 * x0 = 1
 * num0 = 1 (always)
 * b0 = x0  = 1
 * a0 = num0/(sqrt(2)-b0) = 1/(sqrt(2)-1)
 * x1 = floor(2.414) = 2
 * num1 = (2-b0**2)/num0 = (2-1)/1 = 1
 * b1 = x1 * num1 - b0 = 2*1-1 = 1
 * a1 = num1/sqrt(2)-b1 = 1/(sqrt(2)-1, numi, bi already found, cycle
 * sqrt(2) = 1(2)
 */

private class FractionalSquareRoots(val n: Int) {
    //a_i term is x_i + fraction_i = x_i + num_i/(sqrt(n)-b_i)
    data class FractionTerm (val num:Int, val b:Int)
    data class ATerm (val x: Int, val f: FractionTerm)

    val x0 = Math.floor(Math.sqrt(n.toDouble())).toInt()
    val ai = LinkedHashMap<FractionTerm, ATerm>()
    fun calc() {

        //Check integer square:
        if (x0*x0 == n){
            return
        }

        val num0 = 1
        val b0 = x0*num0
        var f = FractionTerm(num0,b0)
        while (true) {
            val nextTerm = getNextTerm(f)
            if (ai.keys.contains(nextTerm.f)) {
                //Cycle detected
                return
            } else {
                ai[nextTerm.f] = nextTerm
                f = nextTerm.f
            }
        }
    }

    /** given a_i = num_i/(sqrt(n)-b_i)
    * x_i+1 = floor (num_i/sqrt(n)-b_i)
    * a_i+1 = num_i+1/(sqrt(n)-b_i+1)
    *  where num_i+1 = (n-b_i**2)/num_i, b_i+1 = x_i+1*num_i+1-b_i
    **/
    fun getNextTerm (fi: FractionTerm ) : ATerm {
        val x = Math.floor(fi.num / (Math.sqrt(n.toDouble()) - fi.b)).toInt()
        val num = (n-fi.b*fi.b)/fi.num
        val b = x * num - fi.b
        //println ("ai = $x,$num/(sqrt($n)-$b)")
        return ATerm (x, FractionTerm(num, b))
    }

}



