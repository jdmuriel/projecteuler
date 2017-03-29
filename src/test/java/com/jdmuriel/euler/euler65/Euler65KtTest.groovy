package com.jdmuriel.euler.euler65

import com.jdmuriel.euler.utils.Fraction
import spock.lang.Specification

import static com.jdmuriel.euler.euler65.Euler65Kt.getContinuedFractionTermsOfE
import static com.jdmuriel.euler.euler65.Euler65Kt.getConvergentsFromCF

/**
 * Created by jesus on 28/03/2017.
 */
class Euler65KtTest extends Specification {

    def "check first CF terms of e" () {
        when:
        def termsOfE = getContinuedFractionTermsOfE(10)
        then:
        termsOfE == [2,1,2,1,1,4,1,1,6,1]
    }

    def "check first ten convergents of e" () {
        when:
        def termsOfE = getContinuedFractionTermsOfE(10)
        def convergents = getConvergentsFromCF(termsOfE)
        convergents = convergents.collect {new Fraction(it.numerator.toInteger(), it.denominator.toInteger())}
        then:
        convergents == [new Fraction(2,1), new Fraction(3,1),
                        new Fraction(8,3), new Fraction (11,4),
                        new Fraction(19,7), new Fraction(87,32),
                        new Fraction(106,39), new Fraction(193,71),
                        new Fraction(1264,465), new Fraction(1457,536)]
    }
}


