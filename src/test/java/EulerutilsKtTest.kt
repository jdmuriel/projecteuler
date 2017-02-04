import com.jdmuriel.euler.utils.getWordValue
import org.junit.Assert
import org.junit.Test

/**
 * Created by jdmuriel on 04/02/2017
 */
class EulerutilsKtTest {
    @Test
    fun wordValueColinIs53() {
        Assert.assertEquals(53, getWordValue("COLIN"))
    }

    @Test
    fun wordValueSkyIs55() {
        Assert.assertEquals(55, getWordValue("SKY"))
    }

}