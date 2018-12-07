package com.rubenexposito.contactsmarvelapp.common

import java.text.DecimalFormat

fun Double.toPrice(): String {
    return "€${DecimalFormat("#.00").format(this)}"
}