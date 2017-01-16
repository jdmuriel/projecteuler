

/**
 * Created by jesus on 14/01/2017
 * https://projecteuler.net/problem=30
 * Digit fifth powers
 */

/*
 * KOTLIN FEATURES:
 * - for in range
 * - mutable lists
 * - map reduce
 * OTHER COMMENTS:
 * -
 * 79683rd  person to solve this.
 */

fun main (args: Array<String>) {
    //9**5 is 59049. 59049*7 is 413343, the maximum number getSumOfDigitsToPower(x,5) can return for x with 6 digits
    //So, no number of 7 digits can be the sum of 7 fifth powers of 0 to 9. We only have to search from 2 to 999999
    val matches = mutableListOf<Int>()
    for (i in 2..999999) {          //We skip 1 because it is not a sum
        val sum = getSumOfDigitsToPower(i,5)
        if (sum==i) {
            println ("$i -> $sum")
            matches.add (i)
        }
    }
    println ("Number founds: ${matches.size}:\n$matches\nSum: ${matches.sum()}")
}

fun getSumOfDigitsToPower(num:Int, power: Int): Int {
    return getDigits(num)
            .map { digit -> Math.pow (digit.toDouble(),power.toDouble()).toInt() }
            .reduce (Int::plus)
}

//Return digits in num, from least significative to most significative
fun getDigits(num:Int) : List<Int> {
    val digits = mutableListOf<Int>()
    var i = num
    while (i!=0) {
        digits.add(i % 10)
        i /= 10
    }
    return digits
}
