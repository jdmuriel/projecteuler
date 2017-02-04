
package com.jdmuriel.euler.utils

import java.math.BigInteger

/**
 * Created by jesus on 19/05/2016.
 * Common use function and structures for Project Euler problems.
 */

data class Fraction<T> (val numerator: T, val denominator: T)

fun BigInteger.sumOfDigits(): Int =
        this.toString().fold(0) { sum, char -> sum + (char - '0')}

fun Int.toBigInteger(): BigInteger =
        BigInteger.valueOf(this.toLong())


//9 digit number which contais all 1-9 digits
fun isPandigital(i: Int): Boolean {
    return  isPandigital(i.toString())
}

//9 digit string which contais all 1-9 digits
fun isPandigital(s: String): Boolean {
    return  (s.length == 9 && getDifferentDigitsHigherThan0(s) ==9)
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

fun getWordValue (word: String) : Int {
    val values = word.asIterable().map { char -> char - 'A'+1 }
    return values.sum()
}