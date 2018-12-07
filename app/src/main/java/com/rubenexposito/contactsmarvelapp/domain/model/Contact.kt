package com.rubenexposito.contactsmarvelapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(val name: String, val avatar: String? = null, val phone: String? = null, var selected: Boolean = false) :
    Parcelable