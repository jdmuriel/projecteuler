import com.jdmuriel.euler.PrimeGenerator

/**
 * Created by jesus on 04/01/2017.
 */

fun main (args: Array<String>) {
    val primeGen = PrimeGenerator()

    println (primeGen.getPrimes(5))

    val list = primeGen.getPrimes(10001)
    for (i in 0..99) {
        println("Prime $i: ${list[i]}")
    }

    println ("Prime 10001: ${list[10000]}")
}