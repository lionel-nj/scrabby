package org.scrabby.core

import java.security.MessageDigest
import java.util.*

class Block(private val id: Int, private val prevHash: String) {
    private val timestamp = Date().time
    val hash: String = applySha256(id.toString() + prevHash + timestamp.toString())

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
                    "Hash of the previous block:\n" +
                    "%s\n" +
                    "Hash of the block:\n" +
                    "%s", id, timestamp, prevHash, hash)
        println(toReturn)
    }
}
