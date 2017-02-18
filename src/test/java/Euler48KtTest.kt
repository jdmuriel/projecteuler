import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 18/02/2017.
 */
class Euler48KtTest {
    @Test
    fun calc3To3Returns27 () {
        Assert.assertEquals(27,  calcNToNLastDigits(3, 10000000000))
    }

}
