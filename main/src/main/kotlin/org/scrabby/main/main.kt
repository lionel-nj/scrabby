package org.scrabby.main

import org.scrabby.core.Blockchain

fun main(args: Array<String>) {
    val blockchain = Blockchain(10)
    blockchain.print(5)
}
