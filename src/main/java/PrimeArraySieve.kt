package com.jdmuriel.euler

/**
 * Created by jesus on 28/02/2017.
 * Erathostenes' sieve based on a boolean array
 * Only odd numbers>=3 are in the array
 */
class PrimeArraySieve (initialSize:Int = 1000000){

    //boolean array. sieve[j] is true iif j*2+3 is prime
    private var sieve = BooleanArray(0)

    init {
        fillArray(initialSize)
    }


    fun isPrime (n: Int) : Boolean {
        checkSieveSize(n)
        return if (n<=2) n==2 else if (n%2==0) false else sieve[(n-3)/2]
    }

    fun getPrimesUnder(maxPrime: Int) : List <Int>{
        if (maxPrime<2) return listOf() //No primes under 2

        checkSieveSize(maxPrime)

        //construct list to return
        val primeList = mutableListOf(2)
        (0..(maxPrime-3)/2).filter { sieve[it] }.forEach { primeList.add(it * 2 + 3) }
        return primeList
    }

    private fun checkSieveSize(size: Int) {
        if (size>sieve.size*2-1) {
            fillArray (size)
        }
    }

    private fun fillArray (size: Int) : Unit {
        //fill at least in increments of 1000 to avoid too many increments
        val realSize = ((size / 1000) + 1) * 1000

        sieve = BooleanArray(realSize) { it -> true}
        //Iterate on odd numbers up to ceil(sqrt (realSize))
        val sqrt = Math.sqrt (realSize.toDouble()).toInt()+1
        (3..sqrt).step(2).forEach {
            if (sieve[(it-3)/2]) {
                //If prime, mark all multiples as non-primes
                var product = it
                do {
                    product += it*2     //it is odd, product is odd, we add twice it to generate another odd
                    sieve[(product - 3) / 2] = false
                } while (product <= realSize)
            }
        }
    }

}