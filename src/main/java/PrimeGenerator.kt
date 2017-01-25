package com.jdmuriel.euler

import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

/**
 * Created by jesus on 04/01/2017.
 * Prime generator, using Erathosthenes Sieve
 */
class PrimeGenerator {
    private val primes = TreeSet<BigInteger>()
    private var maxChecked = BigInteger.ZERO
    private var nextToCheckAdding2 = false

    //We check numbers x = i*6+-1
    //for each number, we traverse the list of primes higher than 3, up to sqrt(number)
    //if not found, we add it to the list
    //TODO: Add functionality to enlarge an existing sieve
    private fun generateSieve (numberOfPrimes: Int = 0, numberToCheckIfPrime: BigInteger = BigInteger.ZERO) {
        val primeToCheck = BigDecimal(Math.sqrt(numberToCheckIfPrime.toDouble())).toBigInteger()
        primes.clear()
        if (numberOfPrimes == 0 || numberOfPrimes >= 1) {
            primes.add(BigInteger.valueOf(2L))
            maxChecked = BigInteger.valueOf(2L)
        }
        if (numberOfPrimes == 0 || numberOfPrimes >= 2) {
            primes.add(BigInteger.valueOf(3L))
            maxChecked = BigInteger.valueOf(3L)
        }
        if (numberOfPrimes == 0 || numberOfPrimes >= 3 ) {
            primes.add (BigInteger.valueOf(5L))
            maxChecked = BigInteger.valueOf(5L)
        }
        //General case
        nextToCheckAdding2 = true     //wrong, but necessary to start checking 7
        while (!keepFillingSieve(numberOfPrimes, primeToCheck)) {
            //calc next to check
            if (nextToCheckAdding2) {
                maxChecked += BigInteger.valueOf(2L)
            } else {
                maxChecked += BigInteger.valueOf(4L)
            }
            nextToCheckAdding2 = !nextToCheckAdding2
            if (!isDivisorInSieve(maxChecked))
                primes.add(maxChecked)
        }
    }

    private fun keepFillingSieve(
            maxNumberOfPrimes: Int,
            maxPrimeToCheck: BigInteger) : Boolean {
        if (maxNumberOfPrimes>0)
            return primes.size >= maxNumberOfPrimes
        else
            return maxPrimeToCheck<=maxChecked
    }

    private fun isSieveLargeEnough(
            primeToCheck: BigInteger) : Boolean {
        return primeToCheck<=maxChecked*maxChecked
    }

    private fun isDivisorInSieve(number: BigInteger) : Boolean  {
        primes.forEach { prime ->
            if (number.remainder(prime).equals(BigInteger.ZERO)) {
                return true
            } else if (number < prime) {
                return false
            }
        }
        return false
    }

    fun isPrime (number: BigInteger) : Boolean {
        if (number < BigInteger.ZERO) throw IllegalArgumentException()
        if (!isSieveLargeEnough(number)) {
            generateSieve(0, number)
        }
        if (number > maxChecked)
            return !isDivisorInSieve(number)
        else
            return primes.contains(number)
    }

    fun isPrime (number: Int) = isPrime(number.toBigInteger())

    fun getPrimes (primeCount: Int) : List<BigInteger> {
        generateSieve (primeCount)
        return primes.toList()
    }
    fun getPrimesUnder (maxPrime: BigInteger) : List<BigInteger> {
        generateSieve (0, maxPrime)
        var i = maxChecked + BigInteger.ONE
        while (i <= maxPrime) {
            if (!isDivisorInSieve(i)){
                primes.add(i)
            }
            i+= BigInteger.ONE
        }
        maxChecked = maxPrime
        return primes.toList()
    }

}