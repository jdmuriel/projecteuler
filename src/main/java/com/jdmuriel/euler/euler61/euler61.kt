@file:Suppress("DEPRECATION")

package com.jdmuriel.euler.euler61

import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 16/03/2017
 * https://projecteuler.net/problem=61
 * Cyclical figurate numbers
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * -
 * OTHER COMMENTS:
 * -
 * person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private val LIMIT_INF = 1000
private val LIMIT_SUP = 9999
private val octagonals = Iterable{ FunctionLimitIterable(LIMIT_INF, LIMIT_SUP, ::getOctagonal)}.toSet()
private val heptagonals = Iterable{ FunctionLimitIterable(LIMIT_INF, LIMIT_SUP, ::getHeptagonal)}.toSet()
private val hexagonals =  Iterable{ FunctionLimitIterable(LIMIT_INF, LIMIT_SUP, ::getHexagonal)}.toSet()
private val pentagonals =  Iterable{ FunctionLimitIterable(LIMIT_INF, LIMIT_SUP, ::getPentagonal)}.toSet()
private val squares = Iterable{ FunctionLimitIterable(LIMIT_INF, LIMIT_SUP, ::getSquare)}.toSet()
private val triangulars =  Iterable{ FunctionLimitIterable(LIMIT_INF, LIMIT_SUP, ::getTriangular)}.toSet()
private val sets = arrayOf (octagonals, heptagonals, hexagonals, pentagonals, squares, triangulars)
//private val sets = arrayOf (pentagonals, squares, triangulars)

private fun calc(): Unit {

    println (getListOctagonals(1,40))
    println (getListOctagonals(2,39))
    println (getListOctagonals(10,20))
    println (getListOctagonals(1000,9999))

    val solver = Backtrack<Position> (Position(), ::evaluatePosition, ::generateNewPositions)
    println(solver.solve())

}

private data class Position (
    var stage: Int = 0,  //0: initial stage. 1: solving octagonals... 6: solving triangulars
    var setsChecked: MutableSet<Int> = HashSet(),
    var numbers: Array<Int> = arrayOf()
)

//This evaluator does not work because we were finding a cyclic of ordered octagonal, heptagonal...triangular numbers
// and the problem asks for a number of each type, in no specified order.
private fun evaluatePosition (p: Position) : Backtrack.PositionValue {
    if (p.stage<=1) {
        //First stage always matches the condition because we generate it in a special way
        return Backtrack.PositionValue.POSSIBLE_SOLUTION
    } else {
        //check if last element of p.numbers is in one of the still not found sets
        (0..sets.size-1).filter { !p.setsChecked.contains(it) }
                .filter { sets[it].contains(p.numbers[p.stage-1]) }
                .forEach {
                    //This number matches one of the not-yet-found conditions
                    if (p.stage == sets.size) {
                        //Check circularity (first number must start with last digits of last number)
                        if (p.numbers[0]/100 == p.numbers[sets.size-1].mod(100)) {
                            println("Solution found: $p")
                            p.setsChecked.add(it)
                            return Backtrack.PositionValue.SOLUTION
                        } else {
                            println("Almost cyclic solution, but final and initial number do not match: $p")
                            //Continue searching
                        }
                    } else {
                        println ("possible solution: $p")
                        p.setsChecked.add(it)
                        return Backtrack.PositionValue.POSSIBLE_SOLUTION
                    }
                }
        return Backtrack.PositionValue.FAIL
    }
}

//This evaluator does not work because we were finding a cyclic of ordered octagonal, heptagonal...triangular numbers
// and the problem asks for a number of each type, in no specified order.
private fun evaluatePositionWrong (p: Position) : Backtrack.PositionValue {
    if (p.stage<=1)
        return Backtrack.PositionValue.POSSIBLE_SOLUTION
    else  {
        //check if last position in numbers is in the stage set
        if (sets[p.stage-1].contains(p.numbers[p.stage-1])) {
            if (p.stage == sets.size) {
                //Check circularity (first number must start with last digits of last number)
                if (p.numbers[0]/100 == p.numbers[sets.size-1].mod(100)) {
                    println("Solution found: $p")
                    return Backtrack.PositionValue.SOLUTION
                } else {
                    println("Almost cyclic solution, but final and initial number do not match: $p")
                    return Backtrack.PositionValue.FAIL
                }
            } else {
                println ("possible solution: $p")
                return Backtrack.PositionValue.POSSIBLE_SOLUTION
            }
        } else {
            return Backtrack.PositionValue.FAIL
        }
    }
}

//The generator is OK, we can start with octagonals because it is a cycle
private fun generateNewPositions (p:Position) : Iterable<Position> {
    if (p.stage==0) {
        //generate octagonal numbers
        return sets[0].asIterable().map { Position(p.stage + 1, HashSet(setOf(0)), arrayOf(it)) }
    } else {
        //take two last digits and generate 100 possible 4-digit numbers starting with them
        val prefix = p.numbers[p.stage-1].mod(100) * 100
        return (0..99).map { Position(p.stage+1, HashSet(p.setsChecked), p.numbers + (prefix + it)) }
    }
}

//Start searching octagonals.
//For each octagonal, generate next possibilities
//Possibilities are 100 numbers starting by last two digits of octogonals, which are heptagonals.
//This is a backtracking algorithm
class Backtrack<T> (
        val initialPosition:T,
        val evaluatePosition: (T)->PositionValue,
        val generateNewPositions: (T)->Iterable<T> ) {
    enum class PositionValue {FAIL, POSSIBLE_SOLUTION, SOLUTION}
    fun solve () : Pair<T, PositionValue> {
        return solveStep (initialPosition)
    }
    fun solveStep (position: T): Pair <T, PositionValue> {
        val result = evaluatePosition(position)
        when (result) {
            //if solution, return
            PositionValue.SOLUTION -> {
                return Pair (position, result)
            }
            //else if fail, return
            PositionValue.FAIL -> {
                return Pair (position, result)
            }
            //else get and evaluate new positions
            PositionValue.POSSIBLE_SOLUTION -> {
                generateNewPositions(position).forEach {
                    val recurseResult = solveStep (it)
                    if (recurseResult.second == PositionValue.SOLUTION) {
                        //Exit loop
                        return recurseResult
                    }
                }
                //no solution found from this position
                return Pair (position, PositionValue.FAIL)
            }
        }
    }
}

class FunctionLimitIterable (
        from: Int,
        private val upTo: Int,
        private val fn: (Int) -> Int) : Iterator<Int> {

    var n = 0
    var next = 0
    var hasNext = false
    init {
        //Iterate until value>=from
        while (true){
            n++
            next = fn(n)
            if (next >= from) break
        }
        hasNext = (next<=upTo)
    }

    override fun hasNext(): Boolean {
        return hasNext
    }

    override fun next(): Int {
        if (!hasNext) throw IndexOutOfBoundsException("Next value $next outside initial range")
        val valToReturn = next
        //Calculate here if there are more values
        next = fn(++n)
        hasNext = (next<=upTo)
        return valToReturn
    }

}

private fun getListOctagonals(from: Int, upto: Int) : List<Int> {
    return Iterable{ FunctionLimitIterable(from, upto, ::getOctagonal)}.toList()
}

private fun getOctagonal(n:Int) : Int {
    return n*(3*n-2)
}
private fun getHeptagonal(n:Int) : Int {
    return n*(5*n-3)/2
}
private fun getHexagonal(n:Int) : Int {
    return n*(2*n-1)
}
private fun getPentagonal(n:Int) : Int {
    return n*(3*n-1)/2
}
private fun getSquare(n:Int) : Int {
    return n*n
}
private fun getTriangular(n:Int) : Int {
    return n*(n+1)/2
}