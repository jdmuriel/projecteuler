
import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 26/02/2017.
 */
class Euler51KtTest {
    @Test
    fun SubsetsOf2PositionsAreCorrect() {
        val l = getSubsetAux(2)
        Assert.assertEquals(3,l.size)
        Assert.assertTrue( l.contains(setOf(0)))
        Assert.assertTrue( l.contains(setOf(1)))
        Assert.assertTrue( l.contains(setOf(0,1)))
    }
}