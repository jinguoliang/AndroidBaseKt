package com.empty.jinux.baselibaray.encrypt

import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object Encrypt {
    fun md5(text: String): String {
        return encrypt(text, "md5")
    }

    private fun encrypt(text: String, algorithm: String): String {
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance(algorithm)
            //对字符串加密，返回字节数组
            val digest: ByteArray = instance.digest(text.toByteArray())
            return byte2hex(digest)

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    fun hmacSHA512(text: String, key: String): String {
        return mac(text, key, "HmacSHA512")
    }

    /**
     * 生成签名数据_HmacSHA1加密
     *
     * @param text
     * 待加密的数据
     * @param key
     * 加密使用的key
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    @Throws(Exception::class)
    fun mac(text: String, key: String, algorithm: String): String {

        val keyBytes = key.toByteArray()
        // 根据给定的字节数组构造一个密钥。
        val signingKey = SecretKeySpec(keyBytes, algorithm)
        val mac = Mac.getInstance(algorithm)
        mac.init(signingKey)

        val rawHmac = mac.doFinal(text.toByteArray())
        return byte2hex(rawHmac)
    }

    private fun byte2hex(b: ByteArray): String {
        var hex = ""
        var tmp: String
        for (n in b.indices) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            tmp = Integer.toHexString(b[n].toInt() and 0xFF)
            if (tmp.length == 1) {
                hex = hex + "0" + tmp
            } else {
                hex = hex + tmp
            }
        }
        return hex
    }
}
