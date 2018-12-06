package com.rubenexposito.contactsmarvelapp.data

import com.rubenexposito.contactsmarvelapp.data.dto.MarvelCharacter
import com.rubenexposito.contactsmarvelapp.data.network.MarvelApi
import io.reactivex.Single

interface MarvelRepository {
    fun getCharacters(): Single<List<MarvelCharacter>>
}

class MarvelRepositoryImpl(private val marvelApi: MarvelApi) : MarvelRepository {
    override fun getCharacters(): Single<List<MarvelCharacter>> = marvelApi.getCharacters()
}