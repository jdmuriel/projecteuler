
package com.jdmuriel.euler.euler68

import generateOrderedPermutations
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 12/04/2017
 * https://projecteuler.net/problem=68
 * Magic 5-gon ring
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - sortedSet, IntArray, map, reduce, fold, lambdas
 * OTHER COMMENTS:
 * - Generic implementation capable of solving any n-gon ring
 * 15055th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        //solve3gon()
        solve5gon()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

var matchingPermutations = sortedSetOf<String>()
var groups : Array<IntArray> = arrayOf()

/**
 * Generate permutations of digits 1-6, in positions p1..p6
 *      Generate three 3 digits number p4 p1 p2; p5 p2 p3; p6 p3 p1, such that all sum the same
 *      Reorder maintainig clock-wise-ness:
 *          if last 3 digit number < first 3 digit number, put last digit in first position, iterate
 */
private fun solve3gon () {
    matchingPermutations = sortedSetOf<String>()
    //Positions in clockwise order, asuming interior pentagon has positions 1 2 3 , exterior has 4 5 6, with 4 in line with 1 and 2
    groups = arrayOf(intArrayOf(4,1,2), intArrayOf(5,2,3), intArrayOf(6,3,1))
    generateOrderedPermutations(sortedSetOf(1,2,3,4,5,6), Stack()) { checkPermutation(it.toIntArray()) }
    println ("3-gon solution: ${matchingPermutations.max()}")
}

private fun solve5gon () {
    matchingPermutations = sortedSetOf<String>()
    //Positions in clockwise order, asuming interior pentagon has positions 1 2 3 4 5, exterior has 6..10, with 6 in line with 1 and 2
    groups = arrayOf(intArrayOf(6,1,2), intArrayOf(7,2,3), intArrayOf(8,3,4), intArrayOf(9,4,5), intArrayOf(10,5,1))
    //16 digit solutions have number 10 in an outside position. We can suppose it is in the last position and
    // generate permutations only of numbers 1..9
    generateOrderedPermutations(sortedSetOf(1,2,3,4,5,6,7,8,9), Stack()) { list9Digits ->
            val aux = IntArray(10)
            list9Digits.forEachIndexed { index, digit -> aux[index] = digit }
            aux[9] = 10
            checkPermutation(aux)
    }
    println ("5-gon solution: ${matchingPermutations.filter {it.length == 16}.max()}")
}

private fun checkPermutation(digitArray: IntArray): Boolean {
    //Sum of the numbers in each group
    val groupSums = mutableListOf<Int>()
    //String of the numbers of each group, in format |00|00|00| (so that we can order and 10 gets last)
    val groupNumberStrings = mutableListOf<String>()
    groups.forEach { group ->
        val sum = group.map { pos -> digitArray[pos - 1] }.sum()
        val number = group.fold("") { acc, pos -> acc + '|' + String.format("%02d", digitArray[pos - 1]) } + '|'
        groupSums.add(sum)
        groupNumberStrings.add(number)
    }
    groupSums.sort()
    if (groupSums.first() == groupSums.last()) {
        //All groups sum the same, we have found a solution and must order it
        println("Found matching permutation: $groupNumberStrings")
        //Find minimum and move to first position rotating to maintain clockwiseness
        val minimum = groupNumberStrings.min()
        while (groupNumberStrings.first() != minimum) {
            val temp = groupNumberStrings.removeAt(groupNumberStrings.size - 1)
            groupNumberStrings.add(0, temp)
        }
        val s = groupNumberStrings.map { it -> it.replace("|0", "").replace("|", "") }.reduce { acc, s -> acc + s }
        println("Found sorted permutation: $s")
        matchingPermutations.add(s)
    }
    //We must traverse all permutations, return true
    return true
}