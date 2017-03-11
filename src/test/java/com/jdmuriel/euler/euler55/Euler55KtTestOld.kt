package com.jdmuriel.euler.euler55

import com.jdmuriel.euler.utils.toBigInteger
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

/**
 * Created by jesus on 11/03/2017.
 */
class Euler55KtTestOld {
    @Test
    fun oneDigitIsPalindrome () {
        Assert.assertTrue (isPalindrome(0.toBigInteger()))
    }

    @Test
    fun twoDigitsNumberPalindromity () {
        Assert.assertFalse (isPalindrome(31.toBigInteger()))
        Assert.assertTrue (isPalindrome(11.toBigInteger()))
        Assert.assertFalse (isPalindrome(79.toBigInteger()))
        Assert.assertTrue (isPalindrome(88.toBigInteger()))
    }

    @Test
    fun threeDigitPalindrome () {
        Assert.assertTrue (isPalindrome(111.toBigInteger()))
        Assert.assertTrue (isPalindrome(121.toBigInteger()))
        Assert.assertTrue (isPalindrome(989.toBigInteger()))
    }

    @Test
    fun threeDigitNonPalindrome () {
        Assert.assertFalse (isPalindrome(123.toBigInteger()))
        Assert.assertFalse (isPalindrome(112.toBigInteger()))
        Assert.assertFalse (isPalindrome(200.toBigInteger()))
    }

    @Test
    fun getReverseOf0Is0 () {
        Assert.assertEquals (BigInteger.ZERO, getReverse(BigInteger.ZERO))
    }


}