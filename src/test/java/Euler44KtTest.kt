import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 11/02/2017.
 */
class Euler44KtTest {
    @Test
    fun firstTenPentagonNumbersAreOK () {
        val firstPentagonNumbers = longArrayOf(1,5,12,22,35,51,70,92,117,145)
        val test = (1L..10L).map { getPentagonalNumber(it) }
        Assert.assertArrayEquals(firstPentagonNumbers, test.toLongArray())
    }
}