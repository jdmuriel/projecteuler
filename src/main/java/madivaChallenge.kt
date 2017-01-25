
import com.jdmuriel.euler.utils.toBigInteger
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 24/01/2017
 * ***
 * Madiva Challenge
 */

/*
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 *  person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc1()
    }
    println ("Elapsed time: ${"%d".format(time/1000)} (s)")

}

private fun calc1(): Unit {

    val count = 0
    println (hash("javaguay"))

    //val a = "2724207026793"
    val a = "42201887587301538842321190871795270412537671711761118644170793575"
    val alfabeto = "abcdegjkmnoprstuvwyz"
    var b = BigInteger.ZERO
    a.forEach { b = b* BigInteger.TEN + (it-'0').toBigInteger() }

    var c = ""
    while (b> 3.toBigInteger()) {
        c = alfabeto[b.mod(31.toBigInteger()).toInt()] + c
        b /= 31.toBigInteger()
        println ("${b.mod(31.toBigInteger()).toInt()} - $c - $b")
    }
    println ("Count: $count. c:$c")

}


private fun hash(s: String): BigInteger {
    val alfabeto = "abcdegjkmnoprstuvwyz"
    var resultado = 3.toBigInteger()
    s.forEach {
        println("$it - $resultado")
        resultado = resultado * 31.toBigInteger() + alfabeto.indexOf(it).toBigInteger()
    }
    return resultado

}