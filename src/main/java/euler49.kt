
import com.jdmuriel.euler.PrimeGenerator
import com.jdmuriel.euler.utils.toBigInteger
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 18/02/2017
 * https://projecteuler.net/problem=49
 * Prime permutations
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Iterable.map, Iterable.filter
 * - Java ArrayList, ArrayList.addAll, HashSet
 * OTHER COMMENTS:
 * - Only partial permutation check implemented, we found a third solution which does not meet full criteria.
 * 42094th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {

    val primeArray = ArrayList<Int>(1000)
    val primeSet = HashSet<Int>()
    primeArray.addAll (
            PrimeGenerator().getPrimesUnder(10000.toBigInteger())
            .map { it.toInt() }
            .filter { it>=1000 }
    )
    primeSet.addAll (primeArray)

    (0..primeArray.size-3).forEach { i ->
        val p1 = primeArray[i]
        (i+1..primeArray.size-2).forEach { j ->
            val p2 = primeArray[j]
            val p3 = p2 + (p2 - p1)
            if (primeSet.contains ( p3 )) {
                //arithmetic progression of primes. Check if they are permutations
                //(We only check if they have the same digits)
                if (getDigits(p1).containsAll(getDigits(p2)) &&
                        getDigits(p2).containsAll(getDigits(p1)) &&
                        getDigits(p1).containsAll (getDigits(p3))) {
                    println ("Found possible term: $p1, $p2, $p3")
                }
            }
        }
    }

}


