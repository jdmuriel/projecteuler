
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by jesus on 10/01/2017
 * https://projecteuler.net/problem=25
 */

/*
 * KOTLIN FEATURES:
 * - measureTimeMillis
 * - sequences, iterators, BigInteger (Java)
 * 112646th person to solve this.
 */

fun main (args: Array<String>) {
    var index = 0
    val time = measureTimeMillis {
        index = getIndexFirstFibonacciTermWithXDigits(1000)
    }
    println("Index first Fibonacci with 1000 digits: $index; time (s): ${time/1000}")

}

fun getIndexFirstFibonacciTermWithXDigits(digits:Int) : Int {
    var i = 0
    for (fibTerm in Fibonacci()) {
        i++
        //if (i<100) println(fibTerm)
        if (fibTerm.toString(10).length>=digits) {
            return i
        }
    }
    return 0    //Never reached, just to avoid compilation error
}

class Fibonacci : Sequence<BigInteger> {

    class FibonacciIterator : Iterator<BigInteger> {
        private var i = 0L
        private var n = BigInteger.ONE
        private var nplus1 = BigInteger.ONE

        override fun hasNext(): Boolean {
            return true
        }

        override fun next(): BigInteger {
            i++
            if (i <= 2) {
                return BigInteger.ONE
            } else {
                val next = n + nplus1
                n = nplus1
                nplus1 = next
                return next
            }
        }
    }

    override fun iterator(): Iterator<BigInteger> {
        return FibonacciIterator()
    }

}



