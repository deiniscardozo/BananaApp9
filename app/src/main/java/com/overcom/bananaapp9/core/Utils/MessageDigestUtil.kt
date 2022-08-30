package com.overcom.bananaapp9.core.Utils

import java.security.MessageDigest

object MessageDigestUtil {

    fun md5(input:String):String {
        val digest = MessageDigest.getInstance("MD5")
        val result = digest.digest(input.toByteArray())
        println("Después del cifrado md5, antes de convertir a hexadecimal:"+result.size)
        val stringBuilder = StringBuilder()

        result.forEach {
            val value = it
            val hex = value.toInt() and (0xFF)
            val hexStr = Integer.toHexString(hex)
            if(hexStr.length == 1){
                stringBuilder.append("0").append(hexStr)
            }else{
                stringBuilder.append(hexStr)
            }
        }
        return stringBuilder.toString()
    }
}