
import com.google.common.math.LongMath
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 28/02/2017
 * https://projecteuler.net/problem=53
 * Combinatoric selections
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - Only basic math functions
 * - Guava longMath factorial (used only to check result, but wonderful class)
 * OTHER COMMENTS:
 * -
 * 43918th person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private fun calc(): Unit {
    val limit = 1000000
    val result = (1..100).map { getValuesOfNCRUnderLimit(it, limit) }.sum()
    println ("Solution: $result")
}

//Facts:
// nCr = nC(n-r)
// nCr+1 > nCr if r<=n/2
// So we can test nCr with r increasing to n/2, and stop after getting first number > 1000000.
// Rest of values up to nCn-r, including that, will be > 1000000
// It can be done with integer arithmetic, without bigint or even logs.

// 10C2 = 10!/2!/8! = 10*9/(2*1) = 45
// 10C3 = 10!/3!/7! = 10*9*8/(3*2*1) = 120
// 23C10 = 23!/10!/13! = 23*22*21*20*19*18*17*16*15*14/(10*9*8*7*6*5*4*3*2*1)
// Says how many values of r in nCr are less greater than limit
fun getValuesOfNCRUnderLimit(n:Int, limit: Int) : Int {
    //nC1 = n!/1!/(n-1)! = n/1
    //nC2 = n!/2!/(n-2)! = n*n-1/2*1
    //nC3 = n!/3!(n-3)! = n*(n-1)*(n-2)/3*2*1
    var num = n.toLong()
    var denom = 1L
    var i = 1
    var value = num
    while (i<n/2+1 && value<=limit) {
        num*=(n-i)
        i++
        denom*=i
        value = num/denom
    }
    if (value <= limit) {
        //No value over limit
        println("No values over limit in ${n}Cr")
        return 0
    } else {
        //Values over limit are nCi to nC(n-i) = n-i-i+1 = n-2*i+1
        val valueCount = n - 2 * i + 1
        println("${n}Cr has first value ${n}C{$i}=$value over $limit, so there are $valueCount values over limit")
        return valueCount
    }
}

fun waysOfSelectingRFromN (n:Int, r:Int) : Long {
    //n!/(r!(n-r)!)
    //n!/r! = (n*n-1*n-2...r+1!)
    if (r>n)
        throw IllegalArgumentException("Cannot calculate ways of selecting $r from inferior number $n")
    else if (r==n)
        return 1
    else if (r>n/2)
        return factorialDividedByFactorial (n, n-r) / LongMath.factorial(r)
    else
        return factorialDividedByFactorial (n, r) / LongMath.factorial(n-r)
}

fun factorialDividedByFactorial (n: Int, r: Int) : Long {
    if (n==r)
        return 1
    else
        return n.toLong().downTo(r+1L).reduce { i, j -> i*j }
}