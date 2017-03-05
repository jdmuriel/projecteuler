import org.junit.Assert
import org.junit.Test

/**
 * Created by jesus on 05/03/2017.
 */
class Euler53KtTest {

    @Test
    fun That10CiHas5valuesOver100() {
        //10C2 = 45, 10C3=120, values over 100 are 10C3 to 10C7
        Assert.assertEquals(5, getValuesOfNCRUnderLimit(10,100))
    }

    @Test
    fun That23CiHas4valuesOverOneMillion() {
        Assert.assertEquals(4, getValuesOfNCRUnderLimit(23,1000000))
    }

    @Test
    fun That10CiOverOneMillionHas0values() {
        Assert.assertEquals(0, getValuesOfNCRUnderLimit(10,1000000))
    }

    @Test
    fun check23C10 () {
        Assert.assertEquals(1144066,waysOfSelectingRFromN(23,10))
    }

    @Test
    fun That3FactOver2FactIs3() {
        Assert.assertEquals(3, factorialDividedByFactorial(3,2))
    }

    @Test
    fun That23FactOver13FactIsCorrect() {
        val result=23L*22L*21L*20L*19L*18L*17L*16L*15L*14L
        Assert.assertEquals(result, factorialDividedByFactorial(23,13))
    }

}

