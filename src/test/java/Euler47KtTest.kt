import com.jdmuriel.euler.utils.toBigInteger
import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 16/02/2017.
 */
class Euler47KtTest {
    @Test
    fun factorize1Returns1 () {
        val factors = factorize(1.toBigInteger())
        Assert.assertTrue(factors.size==1)
        Assert.assertTrue(factors.elementSet().size == 1)
        Assert.assertTrue(factors.count(1.toBigInteger())==1)
    }

    @Test
    fun factorize2Returns2 () {
        val factors = factorize(2.toBigInteger())
        Assert.assertTrue(factors.size==1)
        Assert.assertTrue(factors.elementSet().size == 1)
        Assert.assertTrue(factors.count(2.toBigInteger())==1)
    }

    @Test
    fun factorize4Returns2Twice () {
        val factors = factorize(4.toBigInteger())
        Assert.assertTrue(factors.size==2)
        Assert.assertTrue(factors.elementSet().size == 1)
        Assert.assertTrue(factors.count(2.toBigInteger())==2)
    }

    @Test
    fun factorizeWithTwoFactors () {
        val factors = factorize(6.toBigInteger())
        Assert.assertTrue(factors.size==2)
        Assert.assertTrue(factors.elementSet().size == 2)
        Assert.assertTrue(factors.count(2.toBigInteger())==1)
        Assert.assertTrue(factors.count(3.toBigInteger())==1)
    }

    @Test
    fun factorizeMultipleFactors () {
        val factors = factorize(300.toBigInteger())
        Assert.assertTrue(factors.size==5)
        Assert.assertTrue(factors.elementSet().size == 3)
        Assert.assertTrue(factors.count(2.toBigInteger())==2)
        Assert.assertTrue(factors.count(3.toBigInteger())==1)
        Assert.assertTrue(factors.count(5.toBigInteger())==2)
    }

    @Test
    fun factorizeBigPrime () {
        val factors = factorize(113.toBigInteger())
        Assert.assertTrue(factors.size==1)
        Assert.assertTrue(factors.elementSet().size == 1)
        Assert.assertTrue(factors.count(113.toBigInteger())==1)
    }
}