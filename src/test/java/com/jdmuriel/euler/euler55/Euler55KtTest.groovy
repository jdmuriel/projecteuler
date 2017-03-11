package com.jdmuriel.euler.euler55

import spock.lang.Unroll

/**
 * Created by jesus on 11/03/2017.
 */
class Euler55KtTest extends spock.lang.Specification {

    @Unroll
    def "check #n #is_isNot palindrome" () {
        expect:
        Euler55Kt.isPalindrome (n.toBigInteger()) == isPal

        where:
        n       | isPal
        0       | true
        1       | true
        9       | true
        10      | false
        11      | true
        22      | true
        98      | false
        100     | false
        101     | true
        102     | false
        998     | false
        999     | true
        1000    | false
        1001    | true

        is_isNot = isPal? "is" : "is not"
    }

    @Unroll
    def "check #n is reversed as #reversed" () {
        expect:
        Euler55Kt.getReverse (n.toBigInteger()) == reversed.toBigInteger()
        where:
        n       | reversed
        0       | 0
        1       | 1
        9       | 9
        10      | 1
        11      | 11
        98      | 89
        100     | 1
        101     | 101
        102     | 201
        998     | 899
        999     | 999
        1000    | 1
        1001    | 1001
    }

    @Unroll
    def "getNextReverseAndAdd of #a is #b" () {
        expect:
        Euler55Kt.getNextReverseAndAdd (a.toBigInteger()) == b.toBigInteger()

        where:
        a       | b
        0       | 0
        1       | 2
        10      | 11
        349     | 1292
        1292    | 4213
        4213    | 7337

    }

    @Unroll
    def "check that #n #is_isNot Lychrel" () {
        expect:
        Euler55Kt.isLychrelUnderTenThousand(n.toBigInteger()) == isLychrel

        where:
        n    | isLychrel
        0    | false
        1    | false
        4994 | true

        is_isNot = isLychrel ? "is" : "is not"
    }
}
