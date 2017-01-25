
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 23/01/2017
 * https://projecteuler.net/problem=36
 * Double-base palindromes
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - for in step
 * - Integer.toString with radix -curiously not available in Kotlin's Int.toString-
 * OTHER COMMENTS:
 * -
 * 65707th person to solve this.
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
    //Numbers divisible by 2 (or by 10) cannot be palindromic, as cannot start by 0
    for (i in (1..1000000).step (2)) {
        if (isPalindrome(i,10)) {
            if (isPalindrome(i,2)) {
                println ("Found: $i (binary ${Integer.toString(i,2)}")
                count++
                sum+=i
            }
        }
    }
    println ("Count: $count. Sum: $sum")
}

fun isPalindrome (num: Int, radix : Int) : Boolean {
    val s = Integer.toString(num, radix)
    return s == s.reversed()
}


