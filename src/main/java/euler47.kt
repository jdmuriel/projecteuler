
import com.google.common.collect.HashMultiset
import com.google.common.collect.Multiset
import com.jdmuriel.euler.PrimeGenerator
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 12/02/2017
 * https://projecteuler.net/problem=47
 * Distinct primes factors
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Pair
 * - Iterable.all ( predicate )
 * - Java ArrayDequeue
 * - Guava HashMultiset
 * OTHER COMMENTS:
 * -
 * 42099th person to solve this.
 */

private val primeGen = PrimeGenerator()

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}


private fun calc(): Unit {
    val CONSECUTIVE = 4
    val solution = ArrayDeque<Pair<BigInteger,Multiset<BigInteger>>>(CONSECUTIVE)
    var found = false

    //Fill solution
    (1..CONSECUTIVE-1).forEach { solution.add(Pair(it.toBigInteger(), factorize(it.toBigInteger()))) }
    var i = CONSECUTIVE.toBigInteger()
    while (!found) {
        solution.add (Pair(i,factorize(i)))
        if (eval(solution, CONSECUTIVE)) {
            println ("Found:")
            println ("$solution")
            found = true
        }
        solution.removeFirst()
        i += BigInteger.ONE
    }
}


private fun eval (solution: Queue<Pair<BigInteger,Multiset<BigInteger>>>, consecutiveNumbers: Int) : Boolean {
    return solution.all { it.second.elementSet().size == consecutiveNumbers }
}

//Return Multiset with prime factors of n
fun factorize (n: BigInteger) : Multiset<BigInteger>{

    if (n == BigInteger.ONE) return HashMultiset.create(listOf(BigInteger.ONE))

    val factors = HashMultiset.create<BigInteger>()
    var aux = n.toBigInteger()
    primeGen.getPrimesUnder(Math.sqrt(n.toDouble()).toBigInteger())
            .forEach { probableFactor ->
        //check factor probableFactor
        while (aux.mod (probableFactor) == BigInteger.ZERO) {
            factors.add (probableFactor)
            aux/=probableFactor
        }
        if ( aux == BigInteger.ONE )
            return factors
    }
    //No more primes, aux must be prime
    factors.add (aux)
    return factors
}