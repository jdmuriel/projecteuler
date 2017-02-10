import com.jdmuriel.euler.utils.getWordValue
import com.jdmuriel.euler.utils.isPandigital
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

    @Test
    fun isPandigitalReturnsFalseForNoPandigitalNumbers() {
        val noPandigital = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 123, 1234,12345, 123456, 1234567,
                12345678, 111111111, 123456788, 1234567890, 102345678)
        noPandigital.forEach { Assert.assertFalse(isPandigital(it)) }
    }

    @Test
    fun isPandigitalReturnsTrueForPandigitalNUmbers() {
        val pandigital = listOf(123456789, 987654321, 567891234)
        pandigital.forEach { Assert.assertTrue(isPandigital(it)) }
    }

}