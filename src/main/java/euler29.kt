
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by jesus on 13/01/2017
 * https://projecteuler.net/problem=29
 */

/*
 * KOTLIN FEATURES:
 * - for in range
 * - measureTimeMillis
 * - Extension function Int.toBigInteger (in eulerutils.kt)
 * - BigInteger, TreeSet (Java)
 * OTHER COMMENTS:
 * -
 * 75809th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        val terms = getDistinctPowers(2, 100)
        println ("Term count: ${99*99}. Distinct term count: ${terms.size}")
        println ("Terms:\n$terms")
    }
    println("Elapsed time (s): ${time/1000}")
}

private fun getDistinctPowers(from:Int, to:Int) : TreeSet<BigInteger> {
    val terms = TreeSet<BigInteger>()
    for (a in from..to) {
        for (b in from..to) {
            val term = a.toBigInteger().pow(b)
            terms.add (term)
        }
    }
    return terms
}