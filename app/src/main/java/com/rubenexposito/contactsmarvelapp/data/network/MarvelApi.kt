package com.rubenexposito.contactsmarvelapp.data.network

import com.rubenexposito.contactsmarvelapp.data.dto.MarvelCharacter
import io.reactivex.Single

interface MarvelApi {

    fun getCharacters(): Single<List<MarvelCharacter>>
}