
import com.google.common.collect.TreeMultimap
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 26/01/2017
 * https://projecteuler.net/problem=39
 * Integer right triangles
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Guava's TreeMultimap.asMap.maxBy
 * - data class with Comparable
 * OTHER COMMENTS:
 * -
 * 53178th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

data class Triangle (val a: Int, val b: Int, val c: Int) : Comparable<Triangle> {
    override fun compareTo(other: Triangle): Int {
        var comp = this.a.compareTo(other.a)
        if (comp!=0) return comp
        comp = this.b.compareTo(other.b)
        if (comp!=0) return comp
        return this.c.compareTo(other.c)
    }
}

private fun calc(): Unit {
    val MAX_PERIMETER = 1000

    var count = 0
    val sols : TreeMultimap<Int, Triangle> = TreeMultimap.create()

    //We check always a<=b<=c; a must be between 1 and PERIMETER/2
    for (a in 1..MAX_PERIMETER-2) {
        for (b in a..MAX_PERIMETER-a-1) {
            for (c in b..MAX_PERIMETER-a-b) {
                count++
                if (isRightAngleTriangle(a,b,c)) {
                    println ("Found for perimeter ${a+b+c}: $a, $b, $c")
                    sols.put (a+b+c, Triangle (a,b,c))
                }
            }
        }
    }
    //Get maximum
    println (sols.asMap().map {  "${it.key} -> ${it.value.size}" })
    val max = sols.asMap().maxBy { it.value.size }
    println ("Maximum: ${max!!.value.size}: $max")
    println ("Count: $count")
}

private fun isRightAngleTriangle (a: Int, b: Int, c: Int) : Boolean {
    return a*a + b*b == c*c
}