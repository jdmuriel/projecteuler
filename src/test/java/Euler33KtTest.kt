
import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 21/01/2017.
 */

class Euler33Test {
    @Test
    fun `49-98 is a digit cancelling fraction` () {
        Assert.assertTrue(checkDigitCancellingFraction (Fraction(49,98)))
    }
    @Test
    fun `49-99 is not a digit cancelling fraction` () {
        Assert.assertFalse(checkDigitCancellingFraction (Fraction(49,99)))
    }
    @Test
    fun `11-20 is not a digit cancelling fraction` () {
        Assert.assertFalse(checkDigitCancellingFraction (Fraction(11,20)))
    }
    @Test
    fun `12-21 is not a digit cancelling fraction because all cancels` () {
        Assert.assertFalse(checkDigitCancellingFraction (Fraction(12,21)))
    }
}

