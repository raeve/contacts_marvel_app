package com.rubenexposito.contactsmarvelapp.common

import android.widget.ImageView
import com.rubenexposito.contactsmarvelapp.R
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?, name: String? = null) {
    if (!url.isNullOrBlank()) {
        Picasso.get().load(url).placeholder(AvatarPlaceholder(name)).transform(CircleTransform()).into(this)
    } else {
        Picasso.get().load(R.drawable.ic_person_24dp).placeholder(AvatarPlaceholder(name)).transform(CircleTransform())
            .into(this)
    }
}