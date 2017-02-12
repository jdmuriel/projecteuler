import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 12/02/2017.
 */
class Euler45KtTest {
    @Test
    fun checkFirstPentagonalsArePentagonals () {
        val pentagonals = arrayOf(1L,5L,12L,22L,35L)
        for (pi in pentagonals) {
            Assert.assertTrue(isPentagonal(pi))
            Assert.assertFalse(isPentagonal(pi+1))
        }
    }
    @Test
    fun checkFirstHexagonalsAreHexagonals () {
        val hexagonals = arrayOf(1L,6L,15L,28L,45L)
        for (pi in hexagonals) {
            Assert.assertTrue(isHexagonal(pi))
            Assert.assertFalse(isHexagonal(pi+1))
        }
    }
}