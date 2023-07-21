package com.geniuz.postemplate.emv.core

import com.geniuz.postemplate.emv.core.emv.CardReaderState
import com.geniuz.postemplate.emv.core.emv.EMVProcess
import com.geniuz.postemplate.emv.core.models.AID
import com.geniuz.postemplate.emv.core.models.CAPK
import com.geniuz.postemplate.emv.core.models.TransactionInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

interface Pos {

    val emvProcessStateFlow: StateFlow<EMVProcess>

    val cardReaderStateFlow: Flow<CardReaderState>

    suspend fun initialize(): Boolean

    fun loadCAPKs(pks: List<CAPK>): Boolean

    fun loadAIDs(aids: List<AID>): Boolean

    suspend fun readCard()

    fun startEmvProcess(transactionInfo: TransactionInfo)

    fun printReceipt()

    fun selectApplicationPosition(index: Int)
}

fun main(){
    val v = "Outside"
    var c = "c"
    val t = Thread{
        println("threading")
        c = "dd"
        println(v)
        println(c)
    }
    t.start()
//    t.join(100000)
    println(c)
    while (t.isAlive){
        println("The value of c is $c")
    }

}


fun performSHA256Hash(input: ByteArray?, seed: ByteArray?): ByteArray? {

    // perform hash of hash
    val md: MessageDigest
    var digest: ByteArray?
    try {
        md = MessageDigest.getInstance("SHA-256")
        md.reset()
        md.update(seed)
        md.update(input)
        digest = md.digest()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        digest = byteArrayOf(0x00)
    }
    return digest
}

fun hex2byte(s: String): ByteArray? {
    return if (s.length % 2 == 0) {
       hex2byte(s.toByteArray(), 0, s.length shr 1)
    } else {
        // Padding left zero to make it even size #Bug raised by tommy
        hex2byte("0$s")
    }
}

fun hex2byte(b: ByteArray, offset: Int, len: Int): ByteArray {
    val d = ByteArray(len)
    for (i in 0 until (len * 2)) {
        val shift = if (i % 2 == 1) 0 else 4
        d[i shr 1] =
            ((d[i shr 1].toInt() or (Char(b[offset + i].toUShort()).digitToIntOrNull(16)
                ?: (-1 shl shift)))).toByte()
    }
    return d
}

//@Throws(DecoderException::class)
//fun generateHash256Value(iso: ByteArray, key: ByteArray): ByteArray? {
//    var hashText: String? = null
//    try {
//        val m = MessageDigest.getInstance("SHA-256")
//        m.update(key, 0, key.size)
//        m.update(iso, 0, iso.size)
//        hashText = b2h(m.digest())
//        hashText = hashText!!.replace(" ", "")
//    } catch (ex: NoSuchAlgorithmException) {
//        println("Hashing ")
//    }
//    if (hashText!!.length < 64) {
//        val numberOfZeroes = 64 - hashText.length
//        var zeroes = ""
//        var temp = hashText
//        for (i in 0 until numberOfZeroes) {
//            zeroes += "0"
//        }
//        temp = zeroes + temp
//        println("Utility :: generateHash256Value :: HashValue with zeroes: {}$temp")
//        return Hex.decodeHex(temp)
//    }
//    return Hex.decodeHex(hashText)
//}