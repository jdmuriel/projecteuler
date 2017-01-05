
import com.jdmuriel.euler.PrimeGenerator
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

/**
 * Created by jesus on 04/01/2017.
 */
class PrimeGeneratorTest {

    @Test
    fun checkCommonPrimes() {
        val primeGen = PrimeGenerator()
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
    fun getPrimes() {
        val primeGen = PrimeGenerator()
        Assert.assertArrayEquals(
                listOf<Long>(2,3,5,7,11).map { i -> BigInteger.valueOf(i) }.toTypedArray(),
                primeGen.getPrimes(5).toTypedArray())
    }

}