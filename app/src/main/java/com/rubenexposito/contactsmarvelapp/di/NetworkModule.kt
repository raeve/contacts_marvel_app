package com.rubenexposito.contactsmarvelapp.di

import com.rubenexposito.contactsmarvelapp.data.MarvelRepository
import com.rubenexposito.contactsmarvelapp.data.MarvelRepositoryImpl
import com.rubenexposito.contactsmarvelapp.data.network.MarvelApi
import com.rubenexposito.contactsmarvelapp.data.network.RetrofitAdapter
import com.rubenexposito.contactsmarvelapp.domain.model.ContactMapper
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideMarvelRepository(): MarvelRepository = MarvelRepositoryImpl(
        RetrofitAdapter.marvelRetrofit.create(MarvelApi::class.java), ContactMapper()
    )
}