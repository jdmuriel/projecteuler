/**
 * Created by jesus on 06/01/2017
 * https://projecteuler.net/problem=22
 */

/* KOTLIN FEATURES:
 * - Anonymous class,
 * - javaClass.getResourceAsStream,
 * - reader.readText extension function
 * - replace, split string functions
 * - sortedBy, map, foldIndexed, sum list functions
 * 97102nd person to solve this.
 */


fun main (args: Array<String>) {
    val text = object {}.javaClass.getResourceAsStream("p022_names.txt")
            .bufferedReader(Charsets.ISO_8859_1)
            .readText()
    val wordList = text.replace("\"","").split(",")
    val sum = calcListTotal(wordList)
    println ("${wordList.size} words, sum=$sum")
}

fun calcListTotal (wordList: List<String>) : Long {
    return wordList
            .sortedBy { it }
            .map {getWordValue(it)}     //obtain Int value for each word
            .foldIndexed(0L) { i, sum, value ->  // Accumulate position * value
                sum+(i+1)*value
            }
}

fun getWordValue (word: String) : Int {
    val values = word.asIterable().map { char -> char - 'A'+1 }
    return values.sum()
    //return word.asIterable().map { char -> char - 'A' }
    //        .sum()
}