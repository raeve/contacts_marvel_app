package com.rubenexposito.contactsmarvelapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(val id: String, val name: String, val avatar: String? = null, val phone: String? = null, var selected: Boolean = false, var split: Double = 0.0) :
    Parcelable {
    override fun equals(other: Any?): Boolean {
        return id == (other as? Contact)?.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}