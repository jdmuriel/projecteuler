
import com.google.common.base.Strings
import com.google.common.primitives.Ints
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 20/02/2017
 * https://projecteuler.net/problem=51
 * Prime digit replacements
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - data class
 * - MutableMap.entries, .containsKey,
 * - Iterable filter, sortedBy, forEach
 * - String replace, substring, toCharArray
 * - Set<Int>
 * - Guava Strings.repeat
 * OTHER COMMENTS:
 * - We start using a generic boolean array prime sieve, much faster than list based PrimeGenerator
 * 23606th person to solve this.
 */


fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    println ("Solution: ${solve(5)}")
    println ("Solution: ${solve(6)}")
}

//Calculate subsets of positions of length n: all the different subsets of positions 0..n-2
//i.e. {{0}, {1}, {2}, {3}, {0,1}, {0,2}, {0,3}, {1,2},...}
//No need to use last position because all primes must be odd, and therefore,
//checking all digits in last position will give at most a 5-element prime group.
//Iterate over primes of length n.
// For prime p, for each subset of positions calculated before, calculate pattern, substituting digit in positions of the subsets
// for symbol *
// For each pattern not yet processed, calculate number of primes generated substituting * for 0..9
//Fill map with group: pattern, number of primes in group, minimum prime
//Get elements in map with 8 primes.

data class PrimeGroup (val pattern: String, val minPrime: Int, val primeCount: Int)

private fun solve (digits: Int) : PrimeGroup {

    val posSets = getPositionSets(digits)

    //get primes between 10..digits-1_zeros..0 and 10..digits_zeros..0
    val min = ("1" + Strings.repeat("0", digits - 1)).toInt()
    val max = ("1" + Strings.repeat("0", digits)).toInt()
    val primeArray= PrimeArraySieve(max)
    val primes = (min..max-1).filter { primeArray.isPrime(it) }.toList()

    //calculate prime groups
    val primeGroupsMap: MutableMap<String, PrimeGroup> = HashMap()
    var maxPrimeCount = 0
    primes.forEach { prime ->
        for (posSet in posSets) {
            val pattern = getPattern(prime, posSet)
            if (!primeGroupsMap.containsKey(pattern)) {
                val primeGroup = analizePrimeGroupPattern(pattern, primeArray)
                primeGroupsMap[pattern] = primeGroup
                maxPrimeCount = Ints.max(maxPrimeCount, primeGroup.primeCount)
            }
        }
    }

    //Extract best group
    println("Found prime groups for $digits digits:")
    val bestEntries = primeGroupsMap.entries.filter { it.value.primeCount == maxPrimeCount }.sortedBy { it.value.primeCount }
    bestEntries.forEach {
        println(it.value)
    }
    return bestEntries[0].value
}

private fun getPattern (prime: Int, positionSet: Set<Int>) : String {
    val primeCharArray = prime.toString().toCharArray()
    for (pos in positionSet){
        primeCharArray[pos] = '*'
    }
    return primeCharArray.joinToString("")
}

/**
 * Analyzes a pattern calculating number of primes matching that pattern and minimum of them
 * @param pattern: A group pattern like 13*93*9, in which * symbols will be replaced by digits 0..9
 * before checking primality
 */
private fun analizePrimeGroupPattern (pattern: String, primeArray: PrimeArraySieve) : PrimeGroup {
    var primeCount = 0
    var minPrime = -1

    //Do not check 0s if first position is a *
    val digitsToCheck = if (pattern.substring(0,1)=="*") '1'..'9' else '0'..'9'
    digitsToCheck.forEach {
        val newVal = pattern.replace('*', it).toInt()
        if (primeArray.isPrime(newVal)) {
            primeCount++
            if (minPrime == -1) minPrime = newVal
        }
    }
    return PrimeGroup (pattern, minPrime, primeCount)
}

fun getPositionSets(n: Int) : MutableList<Set<Int>> = getSubsetAux(n-1)

fun getSubsetAux(n: Int) : MutableList<Set<Int>> {
    if (n==1)
        return mutableListOf(setOf(0))
    else {
        //Add to each set this position
        //aux a temporal list to avoid concurrentModification
        val aux : MutableList<Set<Int>> = mutableListOf()
        aux.add (setOf(n-1))
        for (s in getSubsetAux(n - 1)) {
            aux.add (s)
            aux.add (s + (n-1))
        }
        return aux
    }
}
