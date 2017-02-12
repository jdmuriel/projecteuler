
import com.jdmuriel.euler.utils.isInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 11/02/2017
 * https://projecteuler.net/problem=45
 * Triangular, Pentagonal and Hexagonal
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Math.sqrt, Math.floor
 * OTHER COMMENTS:
 * -
 * 52598th  person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    findNextTriangularPentagonalHexagonal(285)
}

private fun findNextTriangularPentagonalHexagonal ( initialN: Long ): Long {
    var n= initialN + 1
    while (true) {
        val tn = getTriangular(n)
        if ( isPentagonal(tn) && isHexagonal(tn)){
            println ("Found triangular T$n: $tn, which is pentagonal and hexagonal")
            return tn
        }
        n++
    }
}

fun getTriangular(n: Long) : Long {
    return n*(n+1)/2
}

fun isPentagonal (p: Long) : Boolean {
    //Pentagonal(n) = Pn = n(3n-1)/2
    //3n^2 - n - 2Pn = 0
    //n = 1 +-sqrt (1 + 24Pn) / 6
    //Ej: para p=5, 24*5 + 1 = 121, sqrt(121) = 11, 12/6=2.
    return isInteger( (1+Math.sqrt(1+24*p.toDouble()))/6)
}

fun isHexagonal (h: Long) : Boolean {
    //Hexagonal (n) = Hn = n(2n-1)
    //2n^2-n-Hn = 0
    //n = 1 +- sqrt (1 + 8Hn) / 4
    return isInteger ((1+Math.sqrt(1+8*h.toDouble()))/4)
}