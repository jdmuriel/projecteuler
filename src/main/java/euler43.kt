
import com.google.common.collect.Sets
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 04/02/2017
 * https://projecteuler.net/problem=43
 * Sub-string divisibility
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - TreeSet, Comparator.reverseOrder, Stack
 * - Lambda expression to return found values
 * - return@label
 * - Iterable.filter, .forEach
 * - enum classes
 * - EnumSet
 * - Sets.difference
 * OTHER COMMENTS:
 * - First solution is a brute force which checks all permutations and needs 2 seconds.
 * - Second solution is much quicker. It constructs  the required numbers, adding a digit each time.
 * 43126th person to solve this.
 */

fun main (args: Array<String>) {
    var time = measureTimeMillis {
        solution1()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

    time = measureTimeMillis {
        solution2()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")
}

//Brute force generating permutations in descending order and checking substring divisibility condition
private fun solution1() {

    var count = 0
    var sum = 0L

    val digits = TreeSet<Long>(Comparator.reverseOrder<Long>()) //Descending order allows us end recursion when
                                                                //we reach 0 (which can never be highest order digit)
    digits.addAll( 0L..9L )
    val permutation = Stack<Long>()

    //Option 1
    val divisors = arrayOf(2, 3, 5, 7, 11, 13, 17)
    generateOrderedPermutations(digits, permutation) {
        //Check if all conditions are met
        val testNumber = permutation.toLongArray()
        if (testNumber[0] == 0L)
            return@generateOrderedPermutations false    //End recursion
        for (i in 1..7) {
            //Seven conditions to be met; digits i, i + 1, i + 2 must be divisible by prime
            if ((testNumber[i] * 100 + testNumber[i + 1] * 10 + testNumber[i + 2]) % divisors[i - 1] != 0L) {
                return@generateOrderedPermutations true //Invalid permutation, check next one
            }
        }
        val value = testNumber.reduce {a,b -> a*10+b}
        println("Found: $value")
        count++
        sum += value
        return@generateOrderedPermutations true
    }

    println ("Count: $count. Sum: $sum.")

}

//Solution 2: start by 3 digit multiples of 17, then add digits matching conditions
private val allDigits =  EnumSet.allOf (DigitsEnum.ZERO.javaClass)
private fun solution2() {

    var count = 0
    var sum = 0L

    solve (0, EnumSet.noneOf(DigitsEnum.ZERO.javaClass), 1) {
        println ("Found: $it")
        sum += it
        count++
    }
    println ("Count: $count. Sum: $sum.")

}

//To accelerate construction we use an EnumSet (implemented as a bitfield)
private enum class DigitsEnum(val digit: Int ) {
    ZERO (0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9)
}

private fun solve (possibleSolution: Long, digitsUsed: MutableSet<DigitsEnum>, powerOfTen: Long, process : (Long) -> Unit ) {
    //println ("solve: $possibleSolution, digits: $digitsUsed")

    //if 10 digits found, emit solution
    if (digitsUsed.size==10)
        process(possibleSolution)
    else if (digitsUsed.size==0) {
        //generate initial solutions with 3 digits multiples of 17
        (100L..999L).filter(::isThreeDigitCandidate).forEach {
            solve (it, longToDigitSet(it), 1000, process)
        }
    } else {
        val divisorMatchingDigits = mapOf( 4 to 13, 5 to 11, 6 to 7, 7 to 5, 8 to 3, 9 to 2, 10 to 1)
        Sets.difference(allDigits, digitsUsed).asIterable()     //digits not used yet
                .filter {
                    //check if adding digit matches condition
                    isDivisible (it.digit * 100 + possibleSolution / (powerOfTen / 100),
                                 divisorMatchingDigits[digitsUsed.size+1]!! )
                }
                .forEach {
                    //Add digit it to the left
                    digitsUsed.add(it)
                    solve (powerOfTen*it.digit + possibleSolution, digitsUsed, powerOfTen*10, process)
                    digitsUsed.remove(it)
                }
    }

}

fun isDivisible (number: Long, divisor: Int) : Boolean {
    return number % divisor == 0L
}

private fun longToDigitSet (number: Long) : MutableSet<DigitsEnum> {
    var aux = number
    val set = EnumSet.noneOf(DigitsEnum.ZERO.javaClass)
    while (aux != 0L) {
        set.add ( DigitsEnum.values()[(aux%10).toInt()] )
        aux /= 10
    }
    return set
}

private fun isThreeDigitCandidate (i: Long) : Boolean {
    if (i % 17 != 0L)   //Not divisible by 17
        return false
    return longToDigitSet(i).size == 3  //Three different digits
}
