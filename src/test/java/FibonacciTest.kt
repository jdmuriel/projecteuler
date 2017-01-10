import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

/**
 * Created by jesus on 10/01/2017.
 */
class FibonacciTest {
    @Test
    fun First12TermsAreCorrect () {
        val fib = Fibonacci().iterator()
        val first12Terms = (1..12).map { fib.next() }

        Assert.assertArrayEquals(
                listOf(1,1,2,3,5,8,13,21,34,55,89,144).map {BigInteger.valueOf(it.toLong() )}.toTypedArray(),
                first12Terms.toTypedArray())
    }

    @Test
    fun FirstFibonacciTermWith3DigitsHasIndex12 () {
        Assert.assertEquals(12, getIndexFirstFibonacciTermWithXDigits(3))
    }
}