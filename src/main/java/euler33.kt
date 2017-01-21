
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 19/01/2017
 * https://projecteuler.net/problem=33
 * Digit cancelling fractions
 */

/*
 * KOTLIN FEATURES:
 * - Generics
 * - LinkedList.removeFirstOccurrence
 * - String / MutableCollection.toCollection
 * - Iterable.joinToString
 * OTHER COMMENTS:
 * - More general than asked by the problem, we simplify numbers of any length, not only two-digits
 * - There are better options than using a LinkedList to simplify
 * 52296th person to solve this.
 */

data class Fraction<T> (val numerator: T, val denominator: T)

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc(): Unit {
    val list = mutableListOf<Fraction<Int>>()
    var product = Fraction(1,1)
    for (den in 11..99) {
        for (num in 10..den-1) {
            if (den%10==0 && num%10==0) {
                //Trivial case, do not consider
            } else if (checkDigitCancellingFraction(Fraction (num, den))) {
                println("Found: $num/$den")
                list.add (Fraction (num, den))
                product = Fraction (product.numerator * num, product.denominator * den)
            }
        }
    }
    println (product)

}

fun checkDigitCancellingFraction(f: Fraction<Int>) : Boolean {
    var num = f.numerator
    var den = f.denominator
    var simplified = false

    //Simplify
    val digitsNum = LinkedList<Char>()
    val digitsDen = LinkedList<Char>()
    num.toString().toCollection(digitsNum)
    den.toString().toCollection(digitsDen)
    num.toString().forEach {
        if (digitsDen.contains(it)) {
            digitsNum.removeFirstOccurrence(it)
            digitsDen.removeFirstOccurrence(it)
            simplified = true
        }
    }

    if (!simplified) {
        return false
    } else if (digitsNum.size == 0) {
        return false
    } else {
        //parse, compare cross-multiplying
        num = digitsNum.joinToString("").toInt()
        den = digitsDen.joinToString("").toInt()
        return (num != f.numerator && num * f.denominator == den * f.numerator)
    }

}


