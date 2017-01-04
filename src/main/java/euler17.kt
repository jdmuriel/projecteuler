
/**
 * Created by jesus on 22/04/2016.
 * https://projecteuler.net/problem=17
 */

fun main (args: Array<String>) {
    //IntRange(1,999).forEach { println ("$it:${letters(it)}") }
    println ("sum to 5: ${IntRange(1,5).fold (0) { i, j -> i + letters(j).replace(" ", "").length } }")
    println ("sum to 1000: ${IntRange(1,1000).fold (0) { i, j -> i + letters(j).replace(" ", "").length } }")
}

fun letters (l: Int) : String {
    val UNITS = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen","nineteen")
    val TENTHS = listOf ("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    return when (l) {
        in 1..19 ->
            UNITS[l]
        in 20..99 ->
            TENTHS[l/10] + if (l%10==0) "" else " " + UNITS[l%10]
        in 100..999 ->
            UNITS [l/100] + " hundred" + if (l%100==0) "" else " and " + letters(l%100)
        1000 -> "one thousand"
        else -> ""
    }
}

