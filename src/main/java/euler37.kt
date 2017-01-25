import com.jdmuriel.euler.PrimeGenerator
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 25/01/2017
 * https://projecteuler.net/problem=37
 * Truncatable primes
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 * 53726th person to solve this.
 */

private val p = PrimeGenerator()
private var prefixesTested = 0

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc(): Unit {
    p.isPrime(999999999)   //Precalculate sieve

    val truncPrimes = mutableListOf<Int>()
    //truncatable must start -and end- with one of this prime numbers
    for (i in listOf(2,3,5,7)) {
        truncPrimes.addAll( findTruncatablesStartingBy(i))
    }

    val count = truncPrimes.count()
    val sum = truncPrimes.sum()
    println ("Count: $count. Sum: $sum. Prefixes tested: $prefixesTested")
}

private fun findTruncatablesStartingBy(prefix: Int): List<Int> {
    println("Testing $prefix")
    prefixesTested++
    val l = mutableListOf<Int>()
    var testing: Int
    //prefix must be prime
    for (i in (1..9).step(2)) {
        testing = prefix*10+i
        if (p.isPrime(testing)) {
            if (testPrimeSuffixes(testing)) {
                println("Found $testing")
                l.add(testing)
            }
            l.addAll(findTruncatablesStartingBy(testing))
        }
    }
    return l
}

private fun testPrimeSuffixes(n: Int) : Boolean {
    var suffix: Int
    var digits = 10
    do {
        suffix = n % digits
        if (!p.isPrime(suffix)) {
            return false
        }
        digits *= 10
    } while (suffix!=n)
    return true
}
