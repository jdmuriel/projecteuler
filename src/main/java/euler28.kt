/**
 * Created by jesus on 13/01/2017
 * https://projecteuler.net/problem=28
 */

/*
 * KOTLIN FEATURES:
 * - for in range
 * OTHER COMMENTS:
 * - My formula is not the best one, as I failed to see that LCn is n**2 (aggg)
 * 80251st person to solve this.
 */

fun main (args: Array<String>) {
    println ("Sum of spiral square of size 1001: ${sumDiagsSpiralSquare(1001)}")
}

/**
 * Return the sum of dialogs of a square of size columns built spiraling like this
 * 21 22 23 24 25
 * 20  7  8  9 10
 * 19  6  1  2 11
 * 18  5  4  3 12
 * 17 16 15 14 13
 * A square in step N has Size S = N*2-1 rows and cols. 1 for N=1, 3 for N=2, 5 for N=3, ...
 * If the last corner of a square of size N, is LCn,
 *   the value of the corner of the square N+1 is LCn + number of outer cells in that square
 * Number of outer cells in a square of size S is S * 4 - 4 = (S-1)*4 = ((N*2-1)-1)*4
 * Last Corner of the square of step N, LCn is LCn-1 + ((N*2-1)-1)*4 =
 *   LCn-1 + (N*2-2)*4 = (N-1)*2*4 = (N-1)*8,
 *   LCn-1+(N-1)*8, with LC1=1
 * Rest of corners for step N (square size S= N*2-1) are LCn - (S - 1),  LCn - 2*(S-1), LCn - 3 * (S-1)
 * Sum is LCn + LCn - (S-1) + LCn - 2 * (S-1) + LCn - 3 * (S-1) = 4 * LCn - 6 * (S-1)  = 4 * LCn - 12 * (N-1)
 *   For n = 2, S = 3, SumCorners_n = 4 * 9 - 6*2 = 24
 *   For n = 3, S = 5 SumCorners_n = 4 * 25 - 6*4 = 76
 */
fun sumDiagsSpiralSquare (size: Int) : Long {
    val n = size/2 + 1
    var sum = 1L
    var lastCorner = 1
    for (i in (2..n)) {
        lastCorner += (i-1)*8
        sum+= 4 * lastCorner - 12 * (i-1)
    }
    return sum
}