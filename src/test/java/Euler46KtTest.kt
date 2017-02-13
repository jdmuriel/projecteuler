import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 13/02/2017.
 */
class Euler46KtTest {

    @Test
    fun number25IsSumOfPrimeAndTwice() {
        Assert.assertTrue(isSumOfPrimeAndTwiceSquare(25))
    }
    @Test
    fun number137IsSumOfPrimeAndTwice() {
        Assert.assertTrue(isSumOfPrimeAndTwiceSquare(137))
    }
}