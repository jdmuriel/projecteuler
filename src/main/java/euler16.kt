import java.math.BigInteger

/**
* Created by jesus on 22/04/2016.
* https://projecteuler.net/problem=16
*/

fun main (args: Array<String>) {
    println (BigInteger.valueOf(2L).pow(1000).toString().fold (0, { x, y-> x + (y-'0') } ))
}
