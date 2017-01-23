
import com.jdmuriel.euler.PrimeGenerator
import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 22/01/2017.
 */
class Euler35KtTest {
    val p = PrimeGenerator()


    @Test
    fun `19 is not circular prime` () {
        Assert.assertFalse( isCircularPrime(p, 19) )
    }

    @Test
    fun `197 is circular prime` () {
        Assert.assertTrue( isCircularPrime(p, 197) )
    }


    @Test
    fun `there are 13 circular primes under 100` () {
        Assert.assertEquals(13, getCountCircularPrimesUnder(100))
    }

}