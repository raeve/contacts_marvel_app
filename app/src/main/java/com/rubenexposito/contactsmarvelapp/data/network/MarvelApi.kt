package com.rubenexposito.contactsmarvelapp.data.network

import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse

interface MarvelApi {

    fun getCharacters() : GetCharactersResponse
}