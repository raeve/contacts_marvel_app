package com.rubenexposito.contactsmarvelapp.data.network

import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(
        @Query("ts") timestamp: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Single<GetCharactersResponse>
}