import java.util.*

/**
 * Created by jesus on 07/01/2017
 * https://projecteuler.net/problem=23
 */

/* KOTLIN FEATURES:
 * - ranges 1..n, range .toList
 * - for x in range
 * - treeSets (java feature, not Kotlin)
 * 74344th person to solve this.
 */

private val NON_ABUNDANT_LIMIT = 28123
private val FIRST_ABUNDANT_NUMBER = 12

fun main (args: Array<String>) {

    //Calc list of numbers that cannot be expressed as sum of two abundat numbers
    var nonAbundantComposedList = getNumbersNotExpressedAsSumOfTwoAbundant(
            NON_ABUNDANT_LIMIT)
    println ("Non Abundant-Composed List:\n$nonAbundantComposedList\nSize:${nonAbundantComposedList.size}\nSum:${nonAbundantComposedList.sum()}")

    nonAbundantComposedList = getNumbersNotExpressedAsSumOfTwoAbundant(
            NON_ABUNDANT_LIMIT*2)
    println ("Non Abundant-Composed List:\n$nonAbundantComposedList\nSize:${nonAbundantComposedList.size}\nSum:${nonAbundantComposedList.sum()}")
}

fun getNumbersNotExpressedAsSumOfTwoAbundant (maxNumberToCheck: Int) : List<Int> {
    //Special case: return all numbers up to first
    if (maxNumberToCheck < FIRST_ABUNDANT_NUMBER*2) return (1..maxNumberToCheck).toList()

    //Calc list of abundant numbers under maxNumberToCheck
    val abundantNumberSet = getAbundantNumberSet (maxNumberToCheck)
    println ("Abundant List:\n$abundantNumberSet\nSize:${abundantNumberSet.size}")

    //We know first number is 24
    val nonAbundantSum = (1..FIRST_ABUNDANT_NUMBER*2-1).toMutableList()
    for (i in (FIRST_ABUNDANT_NUMBER*2)..maxNumberToCheck)
    {
        //find sum of two abundant numbers matching i
        if (isSumOfTwo(i, abundantNumberSet)==0) {
            //if not find, add i to list of nonabundantsum numbers
            nonAbundantSum.add (i)
        }
    }
    return nonAbundantSum
}

//checks if number is sum of two ints existing in numberSet.
//In that case, returns the first of those numbers
//If the number is not sum of two others, the function returns 0
//Assumes numberSet ordered in ascending order
fun isSumOfTwo (number: Int, numberSet: TreeSet<Int>) : Int {
    for (firstSummand in numberSet) {
        if (numberSet.contains (number-firstSummand)) {
            return firstSummand
        }
    }
    return 0
}

fun getAbundantNumberSet (limit: Int) : TreeSet<Int> {
    val abundantNumberSet = TreeSet<Int>()
    for (i in FIRST_ABUNDANT_NUMBER..limit) {
        val divisorList = getProperDivisors(i)
        if (divisorList.sum() > i) {
            //println("Abundant number $i, sum of divisors:${divisorList.sum()}")
            abundantNumberSet.add (i)
        }
    }
    return abundantNumberSet
}

