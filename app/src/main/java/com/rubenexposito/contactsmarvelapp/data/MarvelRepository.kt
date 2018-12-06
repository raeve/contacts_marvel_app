package com.rubenexposito.contactsmarvelapp.data

import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse
import com.rubenexposito.contactsmarvelapp.data.network.MarvelApi

interface MarvelRepository {
    fun getCharacters(): GetCharactersResponse
}

class MarvelRepositoryImpl(private val marvelApi: MarvelApi) : MarvelRepository {
    override fun getCharacters(): GetCharactersResponse = marvelApi.getCharacters()
}