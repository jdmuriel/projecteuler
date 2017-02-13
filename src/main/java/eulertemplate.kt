
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 12/02/2017
 * https://projecteuler.net/problem=46
 * 	Goldbach's other conjecture
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 * person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0
    var sum = 0

    println ("Count: $count. Sum: $sum")
}

