
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 17/02/2017
 * https://projecteuler.net/problem=48
 * Self powers
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Iterable.reduce, forEach
 * OTHER COMMENTS:
 * -
 * 85125th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}


private fun calc(): Unit {
    val DIGITS = 10
    val modulus = Math.pow(10.0, DIGITS.toDouble()).toLong()
    println ((1L..1000L).reduce { sum, num -> sum + calcNToNLastDigits(num, modulus)})

}

//Calcs last digits of num**num
fun calcNToNLastDigits(num : Long, modulus : Long) : Long {
    var product = 1L
    (1..num).forEach {
        product = (product * num) % modulus
    }
    return product
}

//Check if multiplying 10 6-digit numbers has the same 4 digit ending that multiplying each number and then reducing mod 10000
private fun test() : Unit {
    val numbers = mutableListOf<BigInteger>()
    (1..10).forEach {
        numbers.add ((Math.random()*1000000).toBigInteger())
    }
    val r1 = numbers.reduce { l1, l2 ->
        val x = l1 * l2
        println ("$l1 x $l2 = $x")
        x
        }
    val r2 = numbers.reduce { l1, l2 ->
        val x = (l1 * l2).mod (10000.toBigInteger())
        //println ("$l1 * $l2 % 10000 = $x")
        x
        }
    println ("Product: $r1, last 4 digits: ${r1.mod(10000.toBigInteger())}")
    println ("Product with modulus: $r2")
}

