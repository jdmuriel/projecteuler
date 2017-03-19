
package com.jdmuriel.euler.euler62

import com.google.common.collect.HashMultimap
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 19/03/2017
 * https://projecteuler.net/problem=62
 * Cubic permutations
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - BigInteger arithmetic, pow()
 * - CharArray.sortedArrayDescending, joinToString
 * - Guava's HashMultimap
 * OTHER COMMENTS:
 * -
 * 22585th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val permutatedCubes = HashMultimap.create<String, BigInteger>()
    var i = 0
    var size = 0
    var smaller = BigInteger.ZERO
    while (true) {
        i++
        val cube = i.toBigInteger().pow(3)
        val normalizedPermutation = cube.toString().toCharArray().sortedArrayDescending().joinToString("")
        permutatedCubes.put(normalizedPermutation, cube)
        if (permutatedCubes[normalizedPermutation].count()==5) {
            if (size==0) size = normalizedPermutation.length
            println("5 cubes for permutation $normalizedPermutation of size $size: ${permutatedCubes[normalizedPermutation]}")
            val minCubeInSet = permutatedCubes[normalizedPermutation].min()
            if (smaller == BigInteger.ZERO) {
                smaller = minCubeInSet
                println ("First minimun cube found $smaller")
            } else if (smaller > minCubeInSet) {
                smaller = minCubeInSet
                println ("New minimun cube found $smaller")
            }
        }
        if (size>0 && normalizedPermutation.length>size) {
            //No more cubes with this size, stop
            println("Smaller cube is $smaller")
            return
        }
    }
}

