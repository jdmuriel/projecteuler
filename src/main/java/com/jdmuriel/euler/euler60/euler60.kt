
package com.jdmuriel.euler.euler60

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.jdmuriel.euler.PrimeArraySieve
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by @jdmuriel on 15/03/2017
 * https://projecteuler.net/problem=60
 * Prime pair sets
 *
 * JAVA / KOTLIN / LIBRARIES FEATURES USED:
 * - HashSets
 * OTHER COMMENTS:
 * - Solution found processing prime 1050, 8389, related with 23 other primes
 * Processed prime 1049, 8387. 1048 nodes, 13710 edges, 20642 fully connected sets
 * Processed prime 1050, 8389. 1049 nodes, 13733 edges, 20692 fully connected sets
 * - We need to advance until prime 9973 to be sure that there are no other 5 element set with lesser sum.
 * Processed prime 1228, 9973. 1227 nodes, 18176 edges, 28375 fully connected sets
 * 18682nd person to solve this.
 */

fun main (args: Array<String>) {
    val time = measureTimeMillis {
        calc()
    }
    println ("Elapsed time: ${"%.3f".format(time.toFloat()/1000)} (s)")

}

private val primeGenerator = PrimeArraySieve(100000000) //Enough for 10000 x 10000

//Equivalent to finding maximum fully-connected subnetwork in a graph, where nodes are primes, edges the isPair relation
//This is the clique problem, see https://en.wikipedia.org/wiki/Clique_problem

//As the number of edges is low (764 edges for 167 first primes), we simply maintain a list
// of all fully connected sets as we add new primes to the network.
//The list is indexed by the lesser prime in each set to simplify access
//Stop condition:
//We keep the minimum sum of 4 element subnetworks, minsum4, and of 5 element subnetworks, minsum5
// Once p + minsum4 is higher than minsum5, minsum5 is the value we are looking for.
private fun calc(): Unit {

    val primes = primeGenerator.getPrimesUnder(10000)
    val primeNetwork = PrimeNetwork()
    primes.forEachIndexed { i, prime2 ->
        primes.forEach { prime1 ->
            if (prime1>=prime2) return@forEach
            if (isPair(prime1, prime2)) {
                //println("Add relation $prime1-$prime2")
                primeNetwork.addRelation(prime1, prime2)
            }
        }
        println ("Processed prime $i, $prime2. ${primeNetwork.nodes.size} nodes, ${primeNetwork.edgeCount} edges, " +
                "${primeNetwork.fullyConnectedSets.size()} fully connected sets")
        if (primeNetwork.minSumConnectedSetsSize5>0 &&
                primeNetwork.minSumConnectedSetsSize5<prime2 + primeNetwork.minSumConnectedSetsSize4 )
            return@forEachIndexed
    }

    println ("Solution: ${primeNetwork.minSumConnectedSetsSize5}")

}

//Nodes in PrimeNetwork are primes
//Edges between p1 and p2 indicate that p1p2 and p2p1 are primes.
class PrimeNetwork {
    //Nodes in the network
    var nodes = HashSet<Int>()
    var edgeCount: Int = 0  //for statistical purposes, so that we estimate number of

    //Has all the differente fully connected sets of nodes, indexed by minimum prime
    //As sets are created adding ever-bigger primes, minimum prime in the set is always fixed
    // when first appending the set and it is not needed to reindex the set in the map ever.
    val fullyConnectedSets: Multimap<Int, Set<Int>> = HashMultimap.create()
    var minSumConnectedSetsSize4 = 0
    var minSumConnectedSetsSize5 = 0
    var bestSolution : Set<Int> = setOf()

    fun addRelation(prime1: Int, prime2: Int) {
        edgeCount+=1

        //Add connections between prime1 and prime2
        nodes.add (prime1)
        nodes.add (prime2)
        //Add fullyConnectedSet of size 2
        addFullyConnectedSet (prime1, setOf(prime1,prime2))

        //Check and add new subsets
        //prime2 has no subset associated, as has just been appended and every fully connected
        //subnetwork to which it belongs will have a lesser representant
        val prime1FullyConnectedSets = fullyConnectedSets[prime1]?: emptyList()
        val newFullyConnectedSets: MutableList<Set<Int>> = mutableListOf()
        prime1FullyConnectedSets.forEach { setOfFullyConnectedPrimes ->
            if (setOfFullyConnectedPrimes.all { it == prime1 || isPair(it, prime2) }) {
                //fully connected set, add to network
                //We have to use a temporal newFullyConnectedSets to avoid ConcurrentModificationException
                val newSet = HashSet(setOfFullyConnectedPrimes)
                newSet.add (prime2)
                println ("New fully connected set of 3 or more: $newSet")
                newFullyConnectedSets.add (newSet)
            }
        }
        newFullyConnectedSets.forEach { newSet -> addFullyConnectedSet(prime1, newSet)}
    }

    //registers a new fully connected set
    private fun addFullyConnectedSet (index: Int, set: Set<Int>) {
        fullyConnectedSets.put(index, set)
        //update
        val sum = set.reduce(Int::plus)
        if (set.size==4) {
            if (minSumConnectedSetsSize4 < sum)
                minSumConnectedSetsSize4 = sum
        }
        if (set.size==5) {
            println ("Possible 5-prime set found: $set")
            if (minSumConnectedSetsSize5 < sum) {
                bestSolution = HashSet(set)
                minSumConnectedSetsSize5 = sum
            }
        }
    }
}

fun isPair (prime1: Int, prime2: Int) : Boolean {
    return primeGenerator.isPrime ( Integer.parseInt ( prime1.toString() + prime2.toString()))
            && primeGenerator.isPrime ( Integer.parseInt ( prime2.toString() + prime1.toString()))
}
