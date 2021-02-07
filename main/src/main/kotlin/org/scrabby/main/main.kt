package org.scrabby.main

import org.scrabby.core.Blockchain
import java.util.*

fun main() {
    var scanner: Scanner = Scanner(System.`in`)
    println("Enter how many zeros the hash must start with:")
    var prefix = scanner.nextInt()
    println("Enter the number of blocks in your blockchain:")
    var blockCount = scanner.nextInt()
    val blockchain = Blockchain(blockCount, prefix)
    println("Enter the number of blocks you want to display! This number cannot be bigger than the number of blocks in your chain")
    blockchain.print(scanner.nextInt())
}
