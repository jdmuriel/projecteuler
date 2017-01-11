
import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 11/01/2017.
 */
class Euler26KtTest {
    @Test
    fun getRecurringCycleUnitFractionWorksUnder10() {
        Assert.assertEquals("",getRecurringCycleUnitFraction(1))
        Assert.assertEquals("",getRecurringCycleUnitFraction(2))
        Assert.assertEquals("3",getRecurringCycleUnitFraction(3))
        Assert.assertEquals("",getRecurringCycleUnitFraction(4))
        Assert.assertEquals("",getRecurringCycleUnitFraction(5))
        Assert.assertEquals("6",getRecurringCycleUnitFraction(6))
        Assert.assertEquals("142857",getRecurringCycleUnitFraction(7))
        Assert.assertEquals("",getRecurringCycleUnitFraction(8))
        Assert.assertEquals("1",getRecurringCycleUnitFraction(9))
        Assert.assertEquals("",getRecurringCycleUnitFraction(10))
    }
}