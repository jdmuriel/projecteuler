
import com.google.common.math.IntMath.factorial
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 21/01/2017
 * https://projecteuler.net/problem=34
 * Digit factorials
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Guava IntMath.factorial
 * - range + map + toIntArray
 * OTHER COMMENTS:
 * -
 * 68855th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

//A number of length N has sum of factorials between 1!*N and 9!*N
//9! =362880
//A number of length N as sum of factorials between N and N*362880
//1-> 1 to 1
//2-> 2 to 725.760
//3-> 3 to 1.088.640
//6 -> 6 to 2.177.280
//8 -> 8 to 2.903.040   therefore, numbers of 8 or more digits cannot be expressed as sum of factorials of its digits
//We only have to test from 10 -lower numbers have no sum- to 9.999.999
private fun calc(): Unit {
    var sum = 0L
    var count = 0
    for (n in 10..9999999) {
        if (n == getSumOfFactorialsOfDigits(n.toLong())) {
            println ("Digit factorial found: $n")
            count ++
            sum+=n
        }
    }
    println ("Count: $count. Sum: $sum")
}

val FIRST_TEN_FACTORIALS = (0..9).map { it -> factorial(it)}.toIntArray()

fun getSumOfFactorialsOfDigits(n : Long) : Int {
    var sum = 0
    n.toString().forEach { sum+=FIRST_TEN_FACTORIALS[it-'0'] }
    return sum
}