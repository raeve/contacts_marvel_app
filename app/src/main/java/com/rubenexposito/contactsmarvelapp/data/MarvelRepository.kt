package com.rubenexposito.contactsmarvelapp.data

import com.rubenexposito.contactsmarvelapp.NetworkConfig
import com.rubenexposito.contactsmarvelapp.common.md5
import com.rubenexposito.contactsmarvelapp.data.network.MarvelApi
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.domain.model.ContactMapper
import io.reactivex.Single
import java.util.*

interface MarvelRepository {
    fun getCharacters(): Single<List<Contact>>
}

class MarvelRepositoryImpl(private val marvelApi: MarvelApi, private val contactMapper: ContactMapper) :
    MarvelRepository {
    override fun getCharacters(): Single<List<Contact>> {
        val timestamp = Date().time
        val concatString = timestamp.toString() + NetworkConfig.MARVEL_PUBLIC_KEY + NetworkConfig.MARVEL_PRIVATE_KEY
        return marvelApi.getCharacters(Date().time, NetworkConfig.MARVEL_PUBLIC_KEY, concatString.md5()).map {
            contactMapper.mapMarvel(it)
        }
    }
}