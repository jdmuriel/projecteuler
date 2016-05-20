import com.jdmuriel.eulerutils.sumOfDigits
import java.math.BigInteger

/**
 * Created by jesus on 20/05/2016.
 * https://projecteuler.net/problem=21
 */

//KOTLIN FEATURES:
// for in range
// BigInteger Java library
// extension function BigInteger.sumOfDigits
// functions on separate packages
// packages not equivalent to directories.
// Template strings.

fun main (args: Array<String>) {
    val limit = 100L
    var result=BigInteger.ONE
    for (i in 2..limit) {
        result *= BigInteger.valueOf(i)
    }
    println ("Sum of $limit!=$result: digits=${result.sumOfDigits()}")
}
