import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 01/02/2017.
 */
class Euler40KtTest {
    @Test
    fun FirstSixEvenDigitsAreValid() {
        Assert.assertArrayEquals(intArrayOf(2,4,6,8,1,1), findChampernownePositions(listOf(2,4,6,8,10,12)).toIntArray())
    }

}