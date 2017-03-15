
package com.jdmuriel.euler.euler59

import com.google.common.collect.HashMultiset
import com.google.common.io.Resources
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 14/03/2017
 * https://projecteuler.net/problem=59
 * XOR decryption
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Ranges with steps
 * - Iterable.filterIndexed, map, forEachIndexed, mapIndexed
 * - Guava HashMultiset (for histograms)
 * - Guava Resources.toString, Resources.getResource
 * OTHER COMMENTS:
 * - Nice problem!
 * 30977th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val CIPHER_LENGTH = 3
    val FILE_CIPHER = "p059_cipher.txt"
    val line = Resources.toString (Resources.getResource(FILE_CIPHER), Charsets.ISO_8859_1)
    val codeArray = line.split(",").map (String::toInt)

    println( subList (codeArray,0,CIPHER_LENGTH))
    println( subList (codeArray,1,CIPHER_LENGTH))
    println( subList (codeArray,2,CIPHER_LENGTH))

    val cipher = IntArray(CIPHER_LENGTH)
    for (i in 0..CIPHER_LENGTH-1) {
        //Analize each position indepenently
        val multiSet = HashMultiset.create<Int>()
        //Iterate in ith positions calculating histogram
        (i..(codeArray.size-1) step(CIPHER_LENGTH)).forEach { code ->
            multiSet.add (codeArray[code])
        }
        println ("Histogram for positions $i:")
        val histogramDescending = multiSet.entrySet().sortedByDescending { it.count }
        println (histogramDescending)

        //Supposing more common character is ' ', cipher position i should be more common code XOR ' '
        cipher[i] = histogramDescending[0].element.xor(' '.toInt())
        //println ("Cipher applying ' ' for position $i=${cipher[i]}")
        //println ("Application:\n" + subList(codeArray,i,CIPHER_LENGTH))
        //println(decodeString(subList(codeArray,i,CIPHER_LENGTH), intArrayOf(cipher[i])))
    }
    println ("Solution:")
    println (decodeString(codeArray, cipher))
    println ("Sum:")
    println (decode(codeArray, cipher).reduce (Int::plus ) )
}

private fun <T>subList(list: List<T>, startIndex: Int, step: Int): List<T> {
    val l : MutableList<T> = mutableListOf()
    list.filterIndexed { i, t -> i>=startIndex}.filterIndexed { i, t -> i%step==0 }.forEach {
        l.add (it)
    }
    return l
}

private fun decode (codes: List<Int>, cipher:IntArray) : List<Int> {
    return codes.mapIndexed { index, code ->
        code.xor(cipher[index%cipher.size]) }
}
private fun decodeString(codes: List<Int>, cipher: IntArray) : String {
    val b = StringBuilder()
    codes.forEachIndexed { i, code ->
        b.append(code.xor(cipher[i%cipher.size]).toChar())
    }
    return b.toString()
}
