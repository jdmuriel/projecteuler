
import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 28/02/2017.
 */
class PrimeArraySieveTest {


    @Test
    fun checkCommonPrimes() {
        val primeGen = PrimeArraySieve(1)
        Assert.assertFalse(primeGen.isPrime(1))
        Assert.assertTrue(primeGen.isPrime(2))
        Assert.assertTrue(primeGen.isPrime(3))
        Assert.assertFalse(primeGen.isPrime(4))
        Assert.assertTrue(primeGen.isPrime(5))
        Assert.assertFalse(primeGen.isPrime(6))
        Assert.assertTrue(primeGen.isPrime(7))
        Assert.assertFalse(primeGen.isPrime(8))
        Assert.assertFalse(primeGen.isPrime(9))
        Assert.assertFalse(primeGen.isPrime(25))
        Assert.assertTrue(primeGen.isPrime(11))
        Assert.assertTrue(primeGen.isPrime(13))
        Assert.assertTrue(primeGen.isPrime(23))
        Assert.assertTrue(primeGen.isPrime(31))
    }

    @Test
    fun first2PrimesAreCalculatedRight() {
        val primeGen = PrimeArraySieve(1)
        Assert.assertArrayEquals(
                intArrayOf(2,3),
                primeGen.getPrimesUnder(3).toIntArray())
    }

    @Test
    fun first5PrimesAreCalculatedRight() {
        val primeGen = PrimeArraySieve(1000)
        Assert.assertArrayEquals(
                intArrayOf(2,3,5,7,11),
                primeGen.getPrimesUnder(11).toIntArray())
    }

    @Test
    fun first5PrimesAreCalculatedRightAfterInitializingWithSizeOne() {
        val primeGen = PrimeArraySieve(1)
        Assert.assertArrayEquals(
                intArrayOf(2,3,5,7,11),
                primeGen.getPrimesUnder(11).toIntArray())
    }

    @Test
    fun thereAre25PrimesUnder100() {
        val primeGen = PrimeArraySieve(1)
        val l = primeGen.getPrimesUnder(100)
        Assert.assertEquals(25, l.size)
    }

}