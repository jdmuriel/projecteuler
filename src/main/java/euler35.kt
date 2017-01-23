
import com.jdmuriel.euler.PrimeGenerator
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 22/01/2017
 * https://projecteuler.net/problem=35
 * Circular primes
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 * 62358th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private val LIMIT = 1000000

private fun calc(): Unit {
    val count = getCountCircularPrimesUnder(LIMIT)
    println ("Count: $count")
}

fun getCountCircularPrimesUnder (limit: Int) : Int {
    val p = PrimeGenerator()
    //pregenerate sieve
    p.isPrime(limit)
    //All primes above 3 are 6*x+-1
    var count = if (limit<2) 0 else if (limit==2) 1 else 2   //2, 3 are circular primes
    var i = 6
    while (i < limit) {
        if ( isCircularPrime(p, i-1) ) {
            count++
            println("Found: ${i - 1}")
        }
        if ( isCircularPrime(p, i+1) ) {
            count++
            println("Found: ${i+1}")
        }
        i+=6
    }
    return count
}

fun isCircularPrime (p: PrimeGenerator, n: Int) : Boolean {
    var circn = n
    val multiplier = calcMultiplier(n)
    do {
        //rotate
        circn =  (circn % multiplier) * 10 +  circn / multiplier
        if (!p.isPrime(circn))
            return false
    } while (circn != n)
    return true
}

//@return 10**n, where n-1 = number of digits
private fun calcMultiplier (n: Int) : Int {
    var aux = n / 10
    var mul = 1
    while (aux!=0) {
        mul *= 10
        aux /= 10
    }
    return mul
}