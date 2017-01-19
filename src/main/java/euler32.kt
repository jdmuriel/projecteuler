
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 18/01/2017
 * https://projecteuler.net/problem=32
 * Pandigital products
 */

/*
 * KOTLIN FEATURES:
 * - for in range with downTo
 * - IntArray
 * - String iterator
 * OTHER COMMENTS:
 * - Brute force :-P
 * - isPandigital test not very well done, but quick to implement
 * 51387th person to solve this.
 */


fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0
    var sum = 0

    //Products using 9 differents digits 1 to 9 must be 99x999=9999 or 9x9999=9999
    //Cannot
    for (i in 9999 downTo 1000) {
        if (testProduct(i)) {
            count++
            sum+=i
        }
    }
    println ("Pandigital products: $count, sum: $sum")
}

private fun testProduct (product: Int): Boolean {
    for (j in 99 downTo 2) {
        if (product % j == 0) {
            if (isPandigital(product, j, product / j)) {
                println("${product / j} x $j = $product")
                return true
            }
        }
    }
    return false
}

private fun isPandigital(i: Int, j: Int, k:Int): Boolean {
    val s = i.toString() + j.toString() + k.toString()
    return  (s.length == 9 && getDifferentDigitsHigherThan0(s)==9)
}
private fun getDifferentDigitsHigherThan0(s: String): Int {
    val array = IntArray(10)
    var count = 0
    for (c in s.iterator()){
        if ((c-'0')>0 && array[c-'0']==0) {
            //Higher than 0 and not already present
            count++
        }
        array[c-'0']=1
    }
    return count
}