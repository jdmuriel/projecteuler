
import com.jdmuriel.euler.utils.isPandigital
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 26/01/2017
 * https://projecteuler.net/problem=38
 * Pandigital multiples
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 * 45743rd person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc(): Unit {

    var maxFound = ""

    //Maximum number to test x must be concatenation of x*1 + x*2, so 5 digits at most)
    var product : String
    for (i in 192..49999) {
        product = ""
        for (j in 1..9) {
            product += (i * j).toString()
            if (product < maxFound) {
                //This cannot be the maximum
                break
            }
            if (product.length==9) {
                if (isPandigital(product)) {
                    println ("Found: $i x 1..$j = $product")
                    if (product>maxFound) maxFound = product
                }
            }
            if (product.length >= 9) {
                //No more pandigital multiples of i
                break
            }
        }
    }

    println ("Max pandigital: $maxFound")
}



