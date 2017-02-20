
import com.jdmuriel.euler.PrimeGenerator
import com.jdmuriel.euler.utils.toBigInteger
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 12/02/2017
 * https://projecteuler.net/problem=46
 * Goldbach's other conjecture
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - TreeSet to hold precalculated twice-squares
 * OTHER COMMENTS:
 * -
 * 44339th person to solve this.
 */

private val primes = PrimeGenerator().getPrimesUnder(100000.toBigInteger())
private val twiceSquares = fillTwiceSquaresUnder(100000)

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")


}

private fun calc(): Unit {
    var i = 1
    while (true) {
        i += 2
        if (!isSumOfPrimeAndTwiceSquare(i)) {
            println("Found: $i")
            return
        }
    }
}

fun isSumOfPrimeAndTwiceSquare (i: Int) : Boolean  {
    for (p in primes) {
        val pp = p.toInt()
        if (pp>i) {
            //No prime lower than i
            return false
        }
        if (pp == i) {
            return true //is prime
        }
        else if (twiceSquares.contains(i-pp)) {
            println ("$i = $p + ${i-pp} = $p + 2 * ${(i-pp)/2}")
            return true
        } else {
            //println ("$i = $p + ${i-pp}")
        }
    }
    return false
}

fun fillTwiceSquaresUnder(limit: Int ): Set<Int> {
    val set = TreeSet<Int>()
    var i = 1
    var number = i * i * 2
    while (number < limit) {
        set.add(number)
        i++
        number = i*i*2
    }
    return set
}
