import com.jdmuriel.euler.PrimeGenerator
import java.math.BigInteger

/**
 * Created by jesus on 10/01/2017
 * https://projecteuler.net/problem=27
 */

/*
 * KOTLIN FEATURES:
 * - downTo ranges,
 * - for in range
 * - sets, bigintegers (java)
 * 63150th  person to solve this.
 */

fun main (args: Array<String>) {
    val primeSet = PrimeGenerator().getPrimesUnder(BigInteger.valueOf(10000000)).toSet()
    println(primeSet)
    var max = 0
    var amax = 0
    var bmax = 0

    for (a in -1000..1000) {
        for (b in -1000..1000) {
            val primeSequenceLength = getPrimeSequenceStartingBy(max, a, b, primeSet)
            if (primeSequenceLength>max) {
                max = primeSequenceLength
                amax = a
                bmax = b
                println ("Found: n2 + ${amax}n + ${bmax} generate $max primes")
            }
        }
    }
    println ("Maximum: n2 + ${amax}n + ${bmax} generate $max primes. Its product is ${amax*bmax}")

}

fun getPrimeSequenceStartingBy(start: Int, a: Int, b:Int, primeSet:Set<BigInteger>) : Int {
    for (test in start downTo 0L){
        val checkPrime = BigInteger.valueOf(test*test + test*a + b)
        if (! primeSet.contains ( checkPrime ))
            return 0
    }
    //Found at least start+1 primes, see how much more primes we get
    var test = start.toLong()
    do {
        test += 1
        val checkPrime = BigInteger.valueOf(test * test + test * a + b)
    } while ( primeSet.contains ( checkPrime ) )
    return test.toInt()     //Last test failed, but we are starting in 0, so count = test-1 + 1
}

