
/**
 * Created by jesus on 18/05/2016.
 * https://projecteuler.net/problem=19
 */

//KOTLIN USES
// when with several values
// if expression
// data class
// while
// val does not mean immutable (const), but inassignable

data class MyDate (var day: Int, var month: Int, var year: Int ) {
    fun nextDay() : Unit {
        val monthDays = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (year%4==0 && (year%100!=0 || year%400==0)) 29 else 28
            else -> 0
        }
        day++
        if (day>monthDays) {
            //month change
            day=1
            month=month%12 + 1
            if (month==1) year+=1
        }
    }
}

fun main (args: Array<String>) {
    val d = MyDate (1, 1, 1900)
    var weekDay=1   //Monday
    var sundays=0
    while (d.year<2001) {
        if (d.day==1 && weekDay==0 && d.year>1900) {
            sundays++
            println("$d was Sunday number $sundays")
        }
        weekDay=(weekDay+1)%7
        d.nextDay()
    }
}
