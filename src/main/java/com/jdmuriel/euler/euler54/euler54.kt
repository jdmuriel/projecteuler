
package com.jdmuriel.euler.euler54

import com.google.common.collect.LinkedHashMultiset
import com.google.common.collect.Multiset.Entry
import com.google.common.io.Resources
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 05/03/2017
 * https://projecteuler.net/problem=54
 * Poker hands
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 * 25622nd person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    var count = 0
    val FILE_POKER = "p054_poker.txt"

    var lineCount = 0
    val lines = Resources.readLines(Resources.getResource(FILE_POKER), Charsets.ISO_8859_1)
    lines.forEach { line ->
        lineCount++
        val hand1 = PokerHand(line.substring(0,15).trim())
        val hand2 = PokerHand(line.substring(15).trim())
        if (hand1.getRankedHand() > hand2.getRankedHand())
            count++
    }

    println ("Lines: $lineCount. Player 1 wins: $count")
}

enum class PokerHandType {
    INVALID_HAND,       //There is something strange here
    HIGH_CARD,          //Highest value card.
    ONE_PAIR,           //Two cards of the same value.
    TWO_PAIRS,          //Two Pairs: Two different pairs.
    THREE_OF_A_KIND,    //Three cards of the same value.
    STRAIGHT,           //All cards are consecutive values.
    FLUSH,              //All cards of the same suit.
    FULL_HOUSE,         //Three of a kind and a pair.
    FOUR_OF_A_KIND,     //Four cards of the same value.
    STRAIGHT_FLUSH,     //All cards are consecutive values of same suit.
    ROYAL_FLUSH         //Ten, Jack, Queen, King, Ace, in same suit.
}

//val suits = arrayOf('H','C', 'S', 'D')
val cardValues = mapOf('2' to 2, '3' to 3, '4' to 4, '5' to 5,
        '6' to 6, '7' to 7, '8' to 8, '9' to 9,
        'T' to 10, 'J' to 11, 'Q' to 12, 'K' to 13, 'A' to 14)

class Card(s: String) {
    val value = s[0]
    val suit = s[1]

    fun intValue () : Int = cardValues[value]!!

    override fun toString() : String = "$value$suit"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Card

        if (value != other.value) return false
        if (suit != other.suit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + suit.hashCode()
        return result
    }
}

@Suppress("ArrayInDataClass")
data class RankedPokerHand (val handType: PokerHandType, val values: IntArray) : Comparable<RankedPokerHand>
{
    override operator fun compareTo(other: RankedPokerHand): Int {
        if (this.handType.ordinal!=other.handType.ordinal)
            return handType.ordinal.compareTo(other.handType.ordinal)
        else {
            var i = 0
            while (values.size>i && other.values.size>i) {
                if (values[i] != other.values[i])
                    return values[i].compareTo(other.values[i])
                i++
            }
            //No difference found in minor size, values are equal
            return 0
        }
    }

}

class PokerHand(cardString: String) {
    val NUM_CARDS = 5

    //Sorted by value descending
    var cards = Array(NUM_CARDS) {Card("  ")}

    init {
        //We could raise exception if two cards are repeated

        val cardList = cardString.split(" ")
        assert(cardList.size==NUM_CARDS)
        cardList.forEachIndexed { index, s ->
            val card = Card(s)
            cards[index] = card
        }
        cards.sortByDescending (Card::intValue)
    }


    fun getRankedHand(): RankedPokerHand {
        val handType = getHandType()
        return RankedPokerHand( handType, getDescendingHistogramByCardValue().map { cardValues[it.element]!! }.toIntArray())
    }

    private fun getHandType(): PokerHandType {
        val countByValue = getDescendingHistogramByCardValue()
        //Analyze number of cards
        when (countByValue[0].count) {
            4 -> return PokerHandType.FOUR_OF_A_KIND
            3 -> //can be THREE_OF_A_KIND or FULL
                return if (countByValue[1].count==2) PokerHandType.FULL_HOUSE else PokerHandType.THREE_OF_A_KIND
            2 -> //can be one or two pairs
                return if (countByValue[1].count==2) PokerHandType.TWO_PAIRS else PokerHandType.ONE_PAIR
            1 -> //all the other hands have no repeated card
                return analyzeNoRepeatedValueHand()
            else ->
                return PokerHandType.INVALID_HAND
        }
    }

    private fun analyzeNoRepeatedValueHand() : PokerHandType {
        //check if all cards have same suit
        if (cards[0].suit == cards[1].suit
                && cards [0].suit == cards[2].suit
                && cards [0].suit == cards[3].suit
                && cards [0].suit == cards[4].suit) {
            //All cards same suit
            //It can be royal flush or straight flush or normal flush
            if (cards[0].value == 'A') {
                //Cards are sorted, if maximum value is A is a royal flush
                return PokerHandType.ROYAL_FLUSH
            } else if (cards[0].intValue() == cards[4].intValue() + 4) {
                //All cards consecutive (we suppose no card is repeated)
                return PokerHandType.STRAIGHT_FLUSH
            } else {
                //All cards same suit, non consecutive
                return PokerHandType.FLUSH
            }
        } else {
            //cards in different suits, we can have straight or high card
            if (cards[0].intValue() == cards[4].intValue() + 4) {
                //All cards consecutive (we suppose no card is repeated)
                return PokerHandType.STRAIGHT
            } else {
                //Nothing
                return PokerHandType.HIGH_CARD
            }
        }
    }

    //Returns: 3 cards of value X, 2 cards of value Y
    //As we are processing cards already sorted in descending order.
    //As sortedByDescending calls Arrays.sort which is stable,
    //we guarrantee that if there are 2 pairs in the original set, both pairs are returned in descending value order
    //Example: receiving QH QD JH JD 7H, multiset will be
    //Q,count=2 J,count=2 7,count=1
    //Stable sort by count will always return first element (Q) with count 2 before second element (J) with count 2
    private fun getDescendingHistogramByCardValue() : Array<Entry<Char>> {

        //Get multiset by card value
        val setByValue = LinkedHashMultiset.create<Char>()
        setByValue.addAll ( cards.map (Card::value) )

        //get multiset entries ordered by cardinality descending
        val descendingHistogram = setByValue.entrySet().sortedByDescending { it.count }.toTypedArray()
        return descendingHistogram
    }

}

