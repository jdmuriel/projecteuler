
import com.jdmuriel.euler.PrimeGenerator
import com.jdmuriel.euler.utils.toBigInteger
import java.util.*
import kotlin.comparisons.reverseOrder
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 02/02/2017
 * https://projecteuler.net/problem=41
 * Pandigital prime
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Functions receiving lambda as parameters
 * - return@label from lambda
 * - Iterable.reduce, MutableCollection.addAll(Iterable)
 * - Range downTo, asIterable
 * - TreeSet, Stack
 * - Kotlin reverseOrder comparator
 * - Generic function
 * OTHER COMMENTS:
 * - Probable not very effective way of finding the solution, but I wanted to build a permutation generator
 * which can be useful for other endeavors (to be generalized and transformed to a class)
 * 49602nd person to solve this.
 */


fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc(): Unit {

    var count = 0

    val symbols = TreeSet<Int>(reverseOrder<Int>())
    //Numbers of 8 or 9 digits 1..N are all divisible by 3, and therefore not prime
    symbols.addAll( (7 downTo 1).asIterable())
    val primes = PrimeGenerator()

    generateOrderedPermutations(symbols, Stack<Int>()) {
        count++
        val number = it.reduce {d1, d2 -> d1*10 + d2 }
        println (number)
        if (primes.isPrime(number.toBigInteger())) {
            println ("Found maximum prime: $number")
            return@generateOrderedPermutations false
        }
        return@generateOrderedPermutations true
    }

    println ("Count: $count.")
}

//calls processor with every permutation using symbols as ordered in SortedSet. Ends when processor returns false
fun <T>generateOrderedPermutations (symbols: SortedSet<T>, currentPermutation: Stack<T>, processor: (List<T>) -> Boolean) : Boolean {
    if (symbols.isEmpty()) {
        return processor (currentPermutation)
    } else {
        for (s in symbols.toList()) {
            symbols.remove (s)
            currentPermutation.add(s)
            if (!generateOrderedPermutations (symbols, currentPermutation, processor))
                return false    //end processing
            else {
                //test next symbol in s
                currentPermutation.pop()
                symbols.add(s)
            }
        }
    }
    return true
}