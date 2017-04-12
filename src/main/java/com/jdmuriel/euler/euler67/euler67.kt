
package com.jdmuriel.euler.euler67

import com.google.common.io.Resources
import com.jdmuriel.euler.utils.max
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 11/04/2017
 * https://projecteuler.net/problem=67
 * Maximum path sum II
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Guava resources
 * - Kotlin Comparable extension function max
 * OTHER COMMENTS:
 * -
 * 73831st person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val triangle = Array(100) {IntArray(100) {-1000}}
    val maxPath = Array(100) {IntArray(100) {-1000}}

    val lines = Resources.readLines(Resources.getResource("p067_triangle.txt"), Charsets.ISO_8859_1)
    lines.forEachIndexed { i, line ->
        val numbers = line.split(' ')
        numbers.forEachIndexed { index, s -> triangle[i][index] = s.toInt() }
    }
    maxPath[0][0] = triangle[0][0]
    for (i in (1..99)) {
        maxPath[i][0]=maxPath[i-1][0]+triangle[i][0]
        maxPath[i][i]=maxPath[i-1][0]+triangle[i][i]
        for (j in (1..i-1)) {
            maxPath[i][j] = max(maxPath[i-1][j-1],maxPath[i-1][j])+triangle[i][j]
        }
    }
    println (maxPath[99].toList())
    val solution = maxPath[99].max()
    println ("Solution: $solution")
}

