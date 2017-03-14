
package com.jdmuriel.euler.utils

import java.math.BigInteger

/**
 * Created by jesus on 19/05/2016.
 * Common use function and structures for Project Euler problems.
 */

data class Fraction<T> (val numerator: T, val denominator: T)

fun BigInteger.sumOfDigits(): Int =
        this.toString().fold(0) { sum, char -> sum + (char - '0')}

fun Number.toBigInteger(): BigInteger {
    if (this is Long )
        return BigInteger.valueOf(this)
    else
        return BigInteger.valueOf(this.toLong())
}

//9 digit number which contais all 1-9 digits
fun isPandigital(i: Int): Boolean {
    return  isPandigital(i.toString())
}

//9 digit number which contais all 1-9 digits
fun is0To9Pandigital(i: Long): Boolean {
    return  is0To9Pandigital(i.toString())
}

//9 digit string which contais all 1-9 digits
fun isPandigital(s: String): Boolean {
    return  (s.length == 9 && getDifferentDigitsHigherThan0(s) ==9)
}

//9 digit string which contais all 1-9 digits
fun is0To9Pandigital(s: String): Boolean {
    return  (s.length == 10 && getDifferentDigitsHigherThan0(s) ==9)
}

private fun getDifferentDigitsHigherThan0(s: String): Int {
    return getDifferentDigits(s, false)
}

private fun getDifferentDigits(s: String, considerDigitZero: Boolean = true): Int {
    val array = IntArray(10)
    var count = 0
    for (c in s.iterator()){
        if (array[c-'0']==0) {
            //Not present yet
            if (considerDigitZero || (c-'0')>0 ) {
                //Include 0 or higher than 0 and not already present
                count++
            }
        }
        array[c-'0']=1
    }
    return count
}

fun getWordValue (word: String) : Int {
    val values = word.asIterable().map { char -> char - 'A'+1 }
    return values.sum()
}

fun isInteger (d: Double) : Boolean {
    return Math.floor(d) == d
}

fun Number.getDigitCount() : Int {
    return this.toString().length
}