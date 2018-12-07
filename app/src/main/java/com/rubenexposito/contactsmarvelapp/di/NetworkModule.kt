package com.rubenexposito.contactsmarvelapp.di

import com.rubenexposito.contactsmarvelapp.data.MarvelRepositoryImpl
import com.rubenexposito.contactsmarvelapp.data.network.MarvelApi
import com.rubenexposito.contactsmarvelapp.data.network.RetrofitAdapter
import com.rubenexposito.contactsmarvelapp.data.mapper.ContactMapper
import com.rubenexposito.contactsmarvelapp.domain.MarvelRepository
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideMarvelRepository(): MarvelRepository = MarvelRepositoryImpl(
        RetrofitAdapter.marvelRetrofit.create(MarvelApi::class.java),
        ContactMapper()
    )
}