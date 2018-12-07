package com.rubenexposito.contactsmarvelapp.domain

import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import io.reactivex.Single

interface MarvelRepository {
    fun getCharacters(limit: Int, offset: Int): Single<MutableList<Contact>>
}
