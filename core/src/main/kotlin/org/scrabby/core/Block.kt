package org.scrabby.core

import java.security.MessageDigest
import java.util.*
import java.util.concurrent.TimeUnit

class Block(private val id: Int, private val prevHash: String, val prefix: Int) {
    private val timestamp = Date().time
    var magicNumber = Random().nextLong();
    var duration: Long = 0
    var hash = mineBlock(prefix)

    private fun mineBlock(prefix: Int): String {
        var blockData = id.toString() + prevHash + timestamp.toString() + magicNumber.toString()
        var now = System.nanoTime()
        var newHash: String = applySha256(blockData)
        val prefixString = String(CharArray(prefix)).replace('\u0000', '0')
        while (newHash.substring(0, prefix) != prefixString) {
            magicNumber += 1
            newHash = applySha256(id.toString() + prevHash + timestamp.toString() + magicNumber.toString())
        }
        duration = System.nanoTime() - now
        return newHash
    }

    private fun applySha256(input: String): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            /* Applies sha256 to our input */
            val hash = digest.digest(input.toByteArray(charset("UTF-8")))
            val hexString = StringBuilder()
            for (elem in hash) {
                val hex = Integer.toHexString(0xff and elem.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            hexString.toString()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun print() {
        val toReturn = String.format(
            "Block:\n" +
                    "Id: %d\n" +
                    "Timestamp: %s\n" +
                    "Magic number: %x\n" +
                    "Hash of the previous block:\n" +
                    "%s\n" +
                    "Hash of the block:\n" +
                    "%s\n" +
                    "Block was generating for %d milliseconds",
            id, timestamp, magicNumber, prevHash, hash, TimeUnit.NANOSECONDS.toMillis(duration)
        )
        println(toReturn)
    }
}
