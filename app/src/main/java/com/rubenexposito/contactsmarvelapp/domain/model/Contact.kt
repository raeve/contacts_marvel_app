package com.rubenexposito.contactsmarvelapp.domain.model

data class Contact(val name: String, val avatar: String? = null, val phone: String? = null, var selected: Boolean = false)