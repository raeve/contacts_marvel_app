package com.rubenexposito.contactsmarvelapp.common

import java.security.MessageDigest

fun String.md5(): String {
    return MessageDigest.getInstance("MD5").digest(toByteArray()).joinToString("") {
        String.format("%02x", it)
    }
}