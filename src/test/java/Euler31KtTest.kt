import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 18/01/2017.
 */
class Euler31KtTest {
    @Test
    fun differentWaysToSum7PenceAre3() {
        val numWays =  getDifferentWaysToSum(7, Coin.values().toList(), Coin.TWO_POUNDS.faceValue, Purse())
        Assert.assertEquals(6, numWays)
    }
}
