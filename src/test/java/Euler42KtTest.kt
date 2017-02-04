import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 04/02/2017
 */
class Euler42KtTest {
    @Test
    fun checkFirst10Triangulars() {
        val triangulars = listOf(1,3,6,10,15,21,28,36,45,55)
        for (i in 1..triangulars.last()) {
            if (i in triangulars){
                Assert.assertTrue(isTriangular(i))
            } else {
                Assert.assertFalse(isTriangular(i))
            }
        }
    }
}