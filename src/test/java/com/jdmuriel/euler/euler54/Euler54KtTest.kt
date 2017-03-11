
package com.jdmuriel.euler.euler54

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by jesus on 09/03/2017.
 */
class Euler54KtTest {

    @Test
    fun readingACardReturnsTheSameCard () {
        val card = Card("4H")
        Assert.assertEquals('H', card.suit)
        Assert.assertEquals('4', card.value)
        Assert.assertEquals(4, card.intValue())
        assertEquals("4H", card.toString())
    }

    @Test
    fun cardValuesAreAsExpected () {
        val values = arrayOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
        values.forEachIndexed { index, c ->
            val card = Card("${c}H")
            Assert.assertEquals(index+2, card.intValue())
        }
    }

    @Test
    fun readingAHandReturnsTheSameHand () {
        val hand = PokerHand ("KD 7S 6S 5C 4H")
        Assert.assertEquals("KD", hand.cards[0].toString())
        Assert.assertEquals("7S", hand.cards[1].toString())
        Assert.assertEquals("6S", hand.cards[2].toString())
        Assert.assertEquals("5C", hand.cards[3].toString())
        Assert.assertEquals("4H", hand.cards[4].toString())
    }

    @Test
    fun cardsInAHandAreSorted () {
        val hand = PokerHand ("4H 5C 6S 7S KD")
        Assert.assertEquals("KD", hand.cards[0].toString())
        Assert.assertEquals("7S", hand.cards[1].toString())
        Assert.assertEquals("6S", hand.cards[2].toString())
        Assert.assertEquals("5C", hand.cards[3].toString())
        Assert.assertEquals("4H", hand.cards[4].toString())
    }

    @Test
    fun handEvaluationDetectsPair () {
        val hand = PokerHand ("5H 5C 6S 7S KD")
        assertEquals(PokerHandType.ONE_PAIR, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsTwoPairs () {
        val hand = PokerHand ("5H 5C 6S 7S 6D")
        assertEquals(PokerHandType.TWO_PAIRS, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsThreeOfAKind () {
        val hand = PokerHand ("5H 5C 7S 5S 6D")
        assertEquals(PokerHandType.THREE_OF_A_KIND, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsPoker () {
        val hand = PokerHand ("5H 5C 5S 7S 5D")
        assertEquals(PokerHandType.FOUR_OF_A_KIND, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsFull () {
        val hand = PokerHand ("5H 5C 5S 7S 7D")
        assertEquals(PokerHandType.FULL_HOUSE, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsHighCard () {
        val hand = PokerHand ("5H 3C 6S 7S JD")
        assertEquals(PokerHandType.HIGH_CARD, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsFlush () {
        val hand = PokerHand ("5H 3H 6H 7H JH")
        assertEquals(PokerHandType.FLUSH, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsStraightFlush () {
        val hand = PokerHand ("5H 3H 4H 7H 6H")
        assertEquals(PokerHandType.STRAIGHT_FLUSH, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsStraight () {
        val hand = PokerHand ("5H 3H 4D 7H 6H")
        assertEquals(PokerHandType.STRAIGHT, hand.getRankedHand().handType)
    }

    @Test
    fun handEvaluationDetectsRoyalFlush () {
        val hand = PokerHand ("AH KH QH TH JH")
        assertEquals(PokerHandType.ROYAL_FLUSH, hand.getRankedHand().handType)
    }

    @Test
    fun twoEqualHandsRankTheSame () {
        val hand1 = PokerHand ("5H 5C 6S 7S KD")
        val hand2 = PokerHand ("7S KD 5H 5C 6S")
        assertTrue(hand1.getRankedHand().compareTo(hand2.getRankedHand())==0)
    }
    @Test
    fun pairOfFivesLosesToPairOfEights () {
        val hand1 = PokerHand ("5H 5C 6S 7S KD")
        val hand2 = PokerHand ("2C 3S 8S 8D TD")
        assertTrue(hand1.getRankedHand().compareTo(hand2.getRankedHand())<0)
    }

    @Test
    fun highestCardAceWinsToHighestCardQueen () {
        val hand1 = PokerHand ("5D 8C 9S JS AC")
        val hand2 = PokerHand ("2C 5C 7D 8S QH")
        assertTrue(hand1.getRankedHand().compareTo(hand2.getRankedHand())>0)
    }

    @Test
    fun threeAcesLosesToFlushWithDiamonds () {
        val hand1 = PokerHand ("2D 9C AS AH AC")
        val hand2 = PokerHand ("3D 6D 7D TD QD")
        assertTrue(hand1.getRankedHand().compareTo(hand2.getRankedHand())<0)
    }

    @Test
    fun pairOfQueensHighestCardNineWinsToPairOfQueensHighestCardSeven () {
        val hand1 = PokerHand ("4D 6S 9H QH QC")
        val hand2 = PokerHand ("3D 6D 7H QD QS")
        assertTrue(hand1.getRankedHand().compareTo(hand2.getRankedHand())>0)
    }

    @Test
    fun fullHouseWithThreeFoursWinsToFullHouseWithThreeThrees () {
        val hand1 = PokerHand ("2H 2D 4C 4D 4S")
        val hand2 = PokerHand ("3C 3D 3S 9S 9D")
        assertTrue(hand1.getRankedHand().compareTo(hand2.getRankedHand())>0)
    }

}