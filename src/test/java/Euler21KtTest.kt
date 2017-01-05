import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 05/01/2017.
 */
class Euler21KtTest {
    @Test
    fun properDivisorsOf220Sum284() {
        Assert.assertArrayEquals(arrayOf(1,2,4,5,10,11,20,22,44,55,110), getProperDivisors(220).toTypedArray())
        Assert.assertTrue( getDivisorsSum(220)==284 )
    }

}