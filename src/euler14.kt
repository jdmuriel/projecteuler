/**
 * Created by jesus on 22/04/2016.
 * https://projecteuler.net/problem=14
 */

fun main (args: Array<String>) {
    var maxC = 0L
    var maxI = 0L

    for (i  in 1L..1000000L) {
        val aux = collatz(i.toLong())
        if (aux > maxC){
            maxC = aux
            maxI = i
        }
        println ("Collatz de $i es $aux. Máximo: $maxI con collatz $maxC ")
    }
}

fun collatz (n : Long) : Long {
    //println (n)
    if (n == 1L)
        return 1
    else if (n%2 == 0L)
        return 1 + collatz(n / 2)
    else
        return 1 + collatz(n * 3 + 1)

}