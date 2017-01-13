import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 13/01/2017.
 */
class Euler28KtTest {
    @Test
    fun testInitialSumDiagsSpiralSquare() {
        Assert.assertEquals(1, sumDiagsSpiralSquare(1))
        Assert.assertEquals(101, sumDiagsSpiralSquare(5))
    }

}