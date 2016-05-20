import junit.framework.TestCase
import org.junit.Test

/**
* Created by jesus on 13/05/2016.
* Test for problem 17.
*/
class Euler17KtTest: TestCase () {

    @Test
    fun testLettersOne() {
        assertEquals(letters(1), "one")
    }

    @Test
    fun testLettersTwo() {
        assertEquals(letters(2), "two")
    }


    @Test
    fun testLettersTen() {
        assertEquals(letters(10), "ten")
    }

    @Test
    fun testLettersEleven() {
        assertEquals(letters(11), "eleven")
    }

    @Test
    fun testLettersTwenty() {
        assertEquals(letters(20), "twenty")
    }

    @Test
    fun testLettersTwentyOne() {
        assertEquals(letters(21), "twenty one")
    }

    @Test
    fun testLettersThirty() {
        assertEquals(letters(30), "thirty")
    }

}