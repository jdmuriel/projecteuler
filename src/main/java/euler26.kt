

/**
 * Created by jesus on 10/01/2017
 * https://projecteuler.net/problem=26
 */

/*
 * KOTLIN FEATURES:
 * - for in range
 * - interpolated strings
 * - Int.mod
 * 60294th  person to solve this.
 */

fun main (args: Array<String>) {
    var maxLength = 0
    for (i in 1..1000-1) {
        val recurringCycle = getRecurringCycleUnitFraction(i)
        if (recurringCycle.length>maxLength) {
            println ("New cycle with maximum length: $i produces $recurringCycle, length: ${recurringCycle.length}")
            maxLength = recurringCycle.length
        }
    }
}


fun getRecurringCycleUnitFraction (divisor:Int): String {
    var pendingDividend = 1
    var result = "0"
    val pendingDividends = mutableListOf<Int>(1)
    //Iterate until finding recurring cycle or exact division
    while (true) {
        pendingDividend *= 10
        //If dividend < divisor, add 0 to cycle
        if (pendingDividend < divisor) {
            result += "0"
        } else {
            result += (pendingDividend / divisor).toString()
            pendingDividend = pendingDividend.mod(divisor)
            if (pendingDividend==0) {
                //No cycle
                return ""
            } else if (pendingDividends.contains(pendingDividend)) {
                //pendingDividend repeated, cycle found
                //length of cycle is position of pendingDividend in set
                val pos = pendingDividends.indexOf(pendingDividend)
                return result.substring(pos+1)
            } else {
                //add to pendingDividends
                pendingDividends.add (pendingDividend)
            }
        }
    }
}
/*
pendingDividend     result       divisor: 7     pendingDividends
        1               0                           [1]
        10 3            01                          [1,3]
        30 2            014                         [1,3,2]
        20 6            0142                        [1,3,2,6]
        60 4            01428                       [1,3,2,6,4]
        40 5            014285                      [1,3,2,6,4,5]
        50 1            0142857                     [1,3,2,6,4,5] Stop, cycle desde pos index+1

        1               0            6              [1]
        10 4            01                          [1,4]
        40 4            016                         [1,4] Stop, return result.substring(2) = "6"
*/