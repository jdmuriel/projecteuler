
import com.google.common.collect.SortedMultiset
import com.google.common.collect.TreeMultiset
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 16/01/2017
 * https://projecteuler.net/problem=31
 * Coin sums
 */

/*
 * KOTLIN FEATURES:
 * - enum class with parameters
 * - List filter, forEach
 * - Guava MultiSet (Java)
 * OTHER COMMENTS:
 * - Rather than finding the number of different ways (which can be done easier by dynamic programming and an array),
 * I find the different ways and print them using a multiset
 * - Curiously, a TreeMultiset is quicker than a HashMultiset (14s versus 16s) ¿?, and produces a nice ordered output
 * 60645th person to solve this.
 */

enum class Coin  (val faceValue:Int){
    TWO_POUNDS(200),
    ONE_POUND(100),
    FIFTY_PENCE(50),
    TWENTY_PENCE(20),
    TEN_PENCE(10),
    FIVE_PENCE(5),
    TWO_PENCE(2),
    ONE_PENCE(1)
}

private val TARGET_VALUE = 200

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        val numWays =  getDifferentWaysToSum(TARGET_VALUE, Coin.values().toList(), Coin.TWO_POUNDS.faceValue, Purse())
        println ("Different ways to sum $TARGET_VALUE: $numWays")
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

class Purse {
    private val coins: SortedMultiset<Coin> = TreeMultiset.create()
    //private val coins: Multiset<Coin> = HashMultiset.create()
    private var totalValue = 0

    fun add(coin: Coin) {
        coins.add(coin)
        totalValue += coin.faceValue
    }

    fun remove(coin:Coin) {
        coins.remove(coin)
        totalValue -= coin.faceValue
    }

    fun getValue(): Int = totalValue

    override fun toString() : String = coins.toString()
}


/*
 * @param currentValue is the sum of values of the coins in currentSelection
 * @return number of different solutions which can be obtained adding availableCurrency
// to currentSelection to obtain targetValue
 */
fun getDifferentWaysToSum (targetValue: Int,
                                  availableCurrency: List<Coin>, maxCurrencyValueToAdd: Int,
                                  currentPurse: Purse) : Int {
    var count = 0

    //We have a solution
    if (targetValue == currentPurse.getValue()) {
        println("Solution found: $currentPurse")
        return 1
    }

    //Recurse
    //To avoid generating repeated selections
    // (e.g, first 1xTWO-PENCE + 1xONE-PENCE, later 1xONE-PENCE + 2xONE-PENCE,
    // we check coins in decreasing face value order, and enter coins in the currentPurse only in decreasing value
    availableCurrency
            .filter {it.faceValue<=maxCurrencyValueToAdd && it.faceValue+ currentPurse.getValue()<=targetValue}
            .forEach {
                currentPurse.add (it)
                count += getDifferentWaysToSum(targetValue, availableCurrency, it.faceValue, currentPurse)
                currentPurse.remove (it)
            }
    return count
}

