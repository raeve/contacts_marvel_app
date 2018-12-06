package com.rubenexposito.contactsmarvelapp.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) = url?.let{
    Picasso.get().load(url).into(this)
}