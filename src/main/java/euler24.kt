import java.util.*
import kotlin.system.exitProcess

/**
 * Created by jesus on 09/01/2017
 * https://projecteuler.net/problem=24
 */

/*
 * Not particularly proud of this code (pretty inefficient), but short and does what needed, and prepared in 30'
 *
 * KOTLIN FEATURES:
 * - TreeSets (Java)
 * - String toList
 * 83289th person to solve this.
 */

private val TARGET = 1000000
private var count = 0

fun main (args: Array<String>) {
    val buffer = CharArray(10)
    val symbolSet = TreeSet<Char>()
    symbolSet.addAll("0123456789".toList())
    traverseOrderedLexicographicPermutations(buffer, 0, symbolSet)
}



fun traverseOrderedLexicographicPermutations(buffer: CharArray, pos: Int, symbolSet: TreeSet<Char>) {
    if (symbolSet.size==1) {
        count++
        //Debug and see first strings are correct
        if (count < 100) {
            buffer[pos] = symbolSet.first()
            println("Permutation $count: ${buffer.toList()}")
        }
        if (count==TARGET) {
            //Desired permutation found
            buffer[pos] = symbolSet.first()
            println("Permutation $count: ${buffer.toList()}")
            exitProcess(0)
        }
        return
    }
    //Prepare set to recurse
    val reducedSet = TreeSet<Char>()
    reducedSet.addAll(symbolSet)
    //Iterate on symbols in order
    for (c in symbolSet) {
        //Recurse on rest of symbols
        reducedSet.remove(c)
        buffer[pos] = c
        traverseOrderedLexicographicPermutations(buffer, pos + 1, reducedSet)
        reducedSet.add(c)
    }
}

