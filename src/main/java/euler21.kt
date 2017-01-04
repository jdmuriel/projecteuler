import java.util.*

/**
 * Created by jesus on 20/05/2016.
 * https://projecteuler.net/problem=21
 */

//KOTLIN FEATURES:

fun main (args: Array<String>) {
    val ph = PrimeHandler() //reuse prime cache
    var amicable: MutableList<Int> = mutableListOf()
    for (i in 1..9999) {
        val friend = getProperDivisors(i, ph).sum()
        //TODO: if friend<i, check if already in list
        if (getProperDivisors(friend, ph).sum() == i) {
            amicable.add (i)
            amicable.add (friend)
        }
    }
    //TODO SUM AND MESSAGE
    //XX
}

//TODO: class with p injected and function getProperDivisors(i)
fun getProperDivisors (i: Int, p: PrimeHandler) : List<Int> {
    return listOf()
}

class PrimeHandler() {
    fun getPrimesInRange(r: IntRange): List<Int> {
        return listOf()
    }
}

fun getList(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList,  object: Comparator<Int> {
        override fun compare(a:Int,b:Int): Int {
            return b-a
        }
    } )
    return arrayList
}