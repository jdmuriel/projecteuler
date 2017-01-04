import java.math.BigInteger

/**
 * Created by jesus on 22/04/2016.
 * https://projecteuler.net/problem=15
 */


//var map : Map<String,BigInteger> = mutableMapOf()

fun main (args: Array<String>) {
    var maxC = 0L
    var maxI = 0L

    var mapPaths: MutableMap<String,BigInteger> = mutableMapOf()

    println ("Calculando latticePaths(20,20)")
    //val l = memoize(mapPaths, ::latticePaths) (16,16)
    val l = latticePathsMap() (20,20)
    println ("Resultado: $l")
}

fun latticePathsMap () : (Int, Int) -> BigInteger {
    val map1 : MutableMap<String, BigInteger> = mutableMapOf()
    var lp : ((Int, Int) -> BigInteger) = fun (x: Int, y: Int): BigInteger {return BigInteger.ZERO}
    lp = fun ( fila: Int, col: Int) : BigInteger {
        var i : BigInteger = map1["$fila-$col"]?:-BigInteger.ONE
        if (i== -BigInteger.ONE) {
            if (fila==0 || col==0) {
                i= BigInteger.ONE
            } else {
                i = lp(fila, col-1) + lp(fila-1, col)
            }
            println ("lp($fila,$col)=$i")
            map1.put ("$fila-$col",i)
        }
        return i
    }
    return lp
}

fun memoize ( map: MutableMap<String, BigInteger>, f : (Int, Int) -> BigInteger) : (Int, Int)->BigInteger {
    return fun ( x: Int, y: Int) : BigInteger {
        var i = map["$x-$y"]
        if (i==null) {
            i = f(x,y)
            map.put ("$x-$y",i)
        }
        return i
    }
}

fun latticePaths(fila: Int, col: Int) : BigInteger {
    var valor = BigInteger.ZERO
    if (fila==0 || col==0) {
        valor= BigInteger.ONE
    } else {
        valor = latticePaths(fila-1, col) + latticePaths(fila, col-1)
    }
    println ("paths($fila,$col)=$valor")
    return valor
}