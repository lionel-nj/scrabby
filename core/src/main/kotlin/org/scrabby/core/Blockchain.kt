package org.scrabby.core

class Blockchain(private val blockCount: Int, private val prefix: Int) {
    private val blocks: MutableList<Block> = mutableListOf()

    init {
        generate()
    }

    private fun generate() {
        blocks.add(Block(1, "0", prefix))
        for (i in 1 until blockCount) {
            blocks.add(Block(i + 1, blocks[i - 1].hash, prefix))
        }
    }

    fun print(count: Int) {
        var i = 0
        while (i < count) {
            if (i == count - 1) {
                blocks[i].print()
                i++
            } else {
                blocks[i].print()
                println()
                i += 1
            }
        }
    }
}
