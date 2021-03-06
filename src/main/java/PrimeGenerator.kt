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
    private var maxChecked = BigInteger.ZERO            //Must be zero or odd>=3
    private var nextToCheckAdding2 = false

    //We check numbers x = i*6+-1
    //for each number, we traverse the list of primes higher than 3, up to sqrt(number)
    //if not found, we add it to the list
    private fun generateSieve (numberOfPrimes: Int = 0, maxInSieve: BigInteger = BigInteger.ZERO) {
        //Fill initial primes
        if (maxChecked== BigInteger.ZERO) {
            primes.addAll(listOf(2, 3, 5).map(Int::toBigInteger))
            maxChecked = 5.toBigInteger()
        }

        if ((maxChecked + BigInteger.ONE).mod (6.toBigInteger()) == BigInteger.ZERO)
            nextToCheckAdding2 = true

        //General case
        while (keepFillingSieve(numberOfPrimes, maxInSieve)) {
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
            return primes.size < maxNumberOfPrimes
        else
            return maxPrimeToCheck > maxChecked
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
            //We need sqrt(number) in sieve
            val primeToCheck = BigDecimal(Math.sqrt(number.toDouble())).toBigInteger()
            generateSieve(0, primeToCheck)
        }
        if (number > maxChecked)
            return !isDivisorInSieve(number)
        else
            return primes.contains(number)
    }

    fun isPrime (number: Int) = isPrime(number.toBigInteger())

    fun getPrimes (primeCount: Int) : List<BigInteger> {
        generateSieve (primeCount)
        return primes.filterIndexed { i, bigInteger -> i<primeCount }
    }
    fun getPrimesUnder (maxPrime: BigInteger) : List<BigInteger> {
        generateSieve (0, maxPrime)
        var i = maxChecked + BigInteger.ONE
        while (i <= maxPrime) {
            if (!isDivisorInSieve(i)){
                primes.add(i)
            }
            i+= BigInteger.valueOf(2L)
        }
        maxChecked = maxPrime
        return primes.filter { it <= maxPrime }
    }

}