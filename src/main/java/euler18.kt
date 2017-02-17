import java.util.*

/**
 * Created by jesus on 22/04/2016.
 * https://projecteuler.net/problem=18
 */

class MaxPath {
    var N = 0
    var array: ArrayList<IntArray> = arrayListOf()
    fun read (sc: Scanner) : Unit {
        N = sc.nextInt()
        array = ArrayList(N)
        for (i in 1..N) {
            array.add ( IntArray(i) {sc.nextInt()} )
        }
    }
    //We calculate maximum value for each cell starting by solution row
    fun solve (): Int {
        //process from longest / solution row to first one
        array.reverse()
        //process every row
        array.forEachIndexed { i, row ->
                if (i>=0)
                    row.forEachIndexed { j, valor -> row[j] += Math.max(array[i-1][j], array[i-1][j+1])  }
            }
        return array[N-1][0]
    }

    fun solve2(): Int {
        for (i in N-2 downTo 0) {
            array[i].forEachIndexed { j, valor -> array[i][j] += Math.max (array[i + 1][j], array[i + 1][j + 1]) }
        }
        return array[0][0]
    }

    fun solve3(): Int {
        for (i in N-2 downTo 0) {
            for (j in 0..i) {
                array[i][j] += Math.max (array[i + 1][j], array[i + 1][j + 1])
            }
        }
        return array[0][0]
    }
}

fun main (args: Array<String>) {
    val input = """
    15
    75
    95 64
    17 47 82
    18 35 87 10
    20 04 82 47 65
    19 01 23 75 03 34
    88 02 77 73 07 63 67
    99 65 04 28 06 16 70 92
    41 41 26 56 83 40 80 70 33
    41 48 72 33 47 32 37 16 94 29
    53 71 44 65 25 43 91 52 97 51 14
    70 11 33 28 77 73 17 78 39 68 17 57
    91 71 52 38 17 14 91 43 58 50 27 29 48
    63 66 04 68 89 53 67 30 73 16 69 87 40 31
    04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
    """
    val sc = Scanner(input)
    val problem = MaxPath()
    problem.read(sc)
    println ("Sol: ${problem.solve()}")
}

