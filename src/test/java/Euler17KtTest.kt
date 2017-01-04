
import org.junit.Assert
import org.junit.Test

/**
* Created by jesus on 13/05/2016.
* Test for problem 17.
*/


class Euler17KtTest  {

    @Test
    fun testLettersOne() {
        Assert.assertEquals(letters(1), "one")
    }

    @Test
    fun testLettersTwo() {
        Assert.assertEquals(letters(2), "two")
    }

    @Test
    fun testLettersTen() {
        Assert.assertEquals(letters(10), "ten")
    }

    @Test
    fun testLettersEleven() {
        Assert.assertEquals(letters(11), "eleven")
    }

    @Test
    fun testLettersTwenty() {
        Assert.assertEquals(letters(20), "twenty")
    }

    @Test
    fun testLettersTwentyOne() {
        Assert.assertEquals(letters(21), "twenty one")
    }

    @Test
    fun testLettersThirty() {
        Assert.assertEquals(letters(30), "thirty")
    }

}