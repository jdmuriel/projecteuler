package com.jdmuriel.euler.utils

/**
 * Created by jesus on 19/03/2017.
 * Find solutions using Backtrack
 * Initiate with a initial position, a function which determines is a position is a final solution, a failed
 *   position, or an intermediate state, and a function which generates new positions from a given one.
 */

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

