package com.rubenexposito.contactsmarvelapp.domain

import com.rubenexposito.contactsmarvelapp.data.MarvelRepository
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetContactsUseCaseTest {

    @Mock
    lateinit var marvelRepository: MarvelRepository

    lateinit var getContactsUseCase: GetContactsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.getContactsUseCase = GetContactsUseCase(marvelRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }


}