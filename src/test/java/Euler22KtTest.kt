import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 06/01/2017.
 */
class Euler22KtTest {
    @Test
    fun wordValue() {
        Assert.assertEquals(53, getWordValue("COLIN"))
    }

    @Test
    fun listTotal() {
        Assert.assertEquals(1 + 4 + 9,  calcListTotal(listOf("A","B","C")))
        Assert.assertEquals(1 + 4 + 9,  calcListTotal(listOf("C","B","A")))
    }

}