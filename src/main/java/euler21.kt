
import java.util.*

/**
 * Created by jesus on 20/05/2016.
 * https://projecteuler.net/problem=21
 */

//KOTLIN FEATURES: for in 1..n, mod, String interpolation, list forEach() and sum()
//104778th person to solve this.

val MAX_NUMBER = 10000-1        //Numbers under 10000
fun main (args: Array<String>) {
    var amicableNumbers = TreeSet<Int>()
    for (i in 1..9999) {
        val divisors = getProperDivisors(i)
        val friend = divisors.sum()
        val friendDivisors = getProperDivisors(friend)
        //println ("Checking $i: divisors=$divisors, friend=$friend, friendDivisors=$friendDivisors")
        if (friend != i && friendDivisors.sum() == i) {
            amicableNumbers.add (i)
            println ("Amicable number $i: divisors=$divisors, friend=$friend, friendDivisors=$friendDivisors")
        }
    }
    println ("${amicableNumbers.size} amicable numbers:")
    amicableNumbers.forEach { println (it) }
    println ("Sum:" + amicableNumbers.fold( 0 ) { R, it -> R + it} )
}

//i>=1
fun getDivisorsSum(i: Int): Int {
    var sum = 1
    for (probableDiv in 2..i-1) {
        if (i.mod(probableDiv) == 0) sum+=probableDiv
    }
    return sum
}

fun getProperDivisors (i: Int) : List<Int> {
    val divisors = mutableListOf(1)
    for (probableDiv in 2..i-1) {
        if (i.mod(probableDiv) == 0) divisors.add(probableDiv)
    }
    return divisors
}

