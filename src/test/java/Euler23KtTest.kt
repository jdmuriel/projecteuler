import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created by jesus on 08/01/2017.
 */
class Euler23KtTest {
    @Test
    fun getAbundantNumbersSetTest() {
        Assert.assertArrayEquals( "No Abundant numbers up to 3", listOf<Int>().toTypedArray(),
                getAbundantNumberSet(3).toTypedArray())
        Assert.assertArrayEquals( "First abundant number is 12", listOf(12).toTypedArray(),
                getAbundantNumberSet(12).toTypedArray())
    }

    @Test
    fun testIsSumOfTwo() {
        val setPosibleSummands = TreeSet<Int>()
        setPosibleSummands.addAll(listOf(1,2,3,8))
        Assert.assertTrue(isSumOfTwo(4, setPosibleSummands)==1)
        Assert.assertTrue(isSumOfTwo(5, setPosibleSummands)==2)
        Assert.assertTrue(isSumOfTwo(6, setPosibleSummands)==3)
        Assert.assertTrue(isSumOfTwo(7, setPosibleSummands)==0)
        Assert.assertTrue(isSumOfTwo(14, setPosibleSummands)==0)
    }

    @Test
    fun getNumbersNotExpressedAsSumOfTwoAbundantsTest() {
        val expected = (1..23).toMutableList()
        Assert.assertArrayEquals(
                expected.toTypedArray(),
                getNumbersNotExpressedAsSumOfTwoAbundant(23).toTypedArray())
        Assert.assertArrayEquals(
                expected.toTypedArray(),
                getNumbersNotExpressedAsSumOfTwoAbundant(24).toTypedArray())
        expected.add (25)
        Assert.assertArrayEquals(
                expected.toTypedArray(),
                getNumbersNotExpressedAsSumOfTwoAbundant(25).toTypedArray())
    }

    @Test
    @org.junit.Ignore
    fun calcSumOfAbundantsFor56246() {
        val abundantsList = getAbundantNumberSet(56246)
        Assert.assertEquals(20,isSumOfTwo(56246,abundantsList))
    }

}