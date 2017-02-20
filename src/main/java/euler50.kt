
import com.jdmuriel.euler.PrimeGenerator
import com.jdmuriel.euler.utils.toBigInteger
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 19/02/2017
 * https://projecteuler.net/problem=50
 * Consecutive prime sum
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - BooleanArray, LongArray
 * - BooleanArray.forEachIndexed
 * OTHER COMMENTS:
 * - Dynamic programming
 * - Not very proud of this code, I initially misunderstood the problem, thinking that I had to check the sums of
 * primes under 1000000, not primes under 1000000
 * 45214th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val pg = PrimeGenerator()
    val isPrime = GetPrimesArray(1000000)
    val primes = ArrayList<Int>()
    isPrime.forEachIndexed { i, b -> if (b) primes.add (i+1) }
    println ("Number of primes < 1000000: ${primes.size}")

    //SCPij = Sum of i to j-esime consecutive primes. SCP[0][0] = 2
    //j>=i
    //SCPij = SCPij-1 + Pj
    //SCPii = Pi
    //Solution is maximum j-i / SCPij is prime, 0<=i<primes.size-1, i<j<primes.size
    val SCPi = LongArray(primes.size)
    var max = 0
    primes.forEachIndexed { i, pi ->
        if (i%1000 == 0) println ("Checking sums starting by $pi")
        SCPi[i] = pi.toLong()
        (i+1..primes.size-1).forEach { j ->
            val pj = primes[j]
            SCPi[j] = SCPi[j-1] + pj.toLong()
            if (SCPi[j] > 1000000) return@forEach
            if (j-i > max) {
                //println ("Checking prime ${SCPi[j]}")
                if (pg.isPrime(SCPi[j].toBigInteger())) {
                    max = j - i
                    println("Found new longest consecutive prime sum: ${j - i + 1} elements " +
                            "between primes $i($pi) and $j($pj): ${SCPi[j]}")
                }
            }
        }
    }
}

fun GetPrimesArray (limit: Int) : BooleanArray {
    val isPrime = BooleanArray(limit) { it -> true}
    val sqrt = Math.sqrt (limit.toDouble()).toInt()+1
    isPrime[0] = false
    (2..sqrt).forEach {
        if (isPrime[it-1]) {
            var product = it * 2
            while (product <= limit) {
                isPrime[product-1] = false
                product += it
            }
        }
    }
    return isPrime
}