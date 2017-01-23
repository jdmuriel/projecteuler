
import com.jdmuriel.euler.PrimeGenerator
import com.jdmuriel.euler.utils.toBigInteger
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

/**
 * Created by jesus on 04/01/2017.
 */
class PrimeGeneratorTest {

    val primeGen = PrimeGenerator()

    @Test
    fun checkCommonPrimes() {
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(2L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(3L)))
        Assert.assertFalse(primeGen.isPrime(BigInteger.valueOf(4L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(5L)))
        Assert.assertFalse(primeGen.isPrime(BigInteger.valueOf(6L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(7L)))
        Assert.assertFalse(primeGen.isPrime(BigInteger.valueOf(8L)))
        Assert.assertFalse(primeGen.isPrime(BigInteger.valueOf(9L)))
        Assert.assertFalse(primeGen.isPrime(BigInteger.valueOf(25L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(11L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(13L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(23L)))
        Assert.assertTrue(primeGen.isPrime(BigInteger.valueOf(31L)))
    }

    @Test
    fun first5PrimesAreCalculatedRight() {
        Assert.assertArrayEquals(
                listOf<Long>(2,3,5,7,11).map { i -> BigInteger.valueOf(i) }.toTypedArray(),
                primeGen.getPrimes(5).toTypedArray())
    }
    @Test
    fun thereAre25PrimesUnder100() {
        val l = primeGen.getPrimesUnder(100.toBigInteger())
        Assert.assertEquals(25, l.size)
    }

}