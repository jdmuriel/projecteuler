
package com.jdmuriel.euler.utils

import java.math.BigInteger

/**
 * Created by jesus on 19/05/2016.
 * Common use function for Project Euler problems.
 */

fun BigInteger.sumOfDigits(): Int =
        this.toString().fold(0) { sum, char -> sum + (char - '0')}

fun Int.toBigInteger(): BigInteger =
        BigInteger.valueOf(this.toLong())
