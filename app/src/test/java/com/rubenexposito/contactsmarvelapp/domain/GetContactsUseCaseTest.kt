package com.rubenexposito.contactsmarvelapp.domain

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.rubenexposito.contactsmarvelapp.domain.interactor.GetContactsUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetContactsUseCaseTest {

    @Mock
    lateinit var contactsRepository: ContactsRepository

    @Mock
    lateinit var marvelRepository: MarvelRepository

    private val useCase by lazy {
        GetContactsUseCase(
                marvelRepository,
                contactsRepository,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should execute on complete when call is successful`() {
        givenRequestSuccessful()
        useCase.execute({
            assert(it.isEmpty())
        }, {}, 0, 10)
    }

    @Test
    fun `should execute on error when call is unsuccessful`() {
        givenRequestUnsuccessful()
        useCase.execute({
        }, { assert(it.message == "marvel not cool") }, 0, 10)
    }

    private fun givenRequestSuccessful(limit: Int = 10, offset: Int = 0) {
        whenever(marvelRepository.getCharacters(limit, offset)).thenReturn(Single.just(ArrayList()))
        whenever(contactsRepository.getPhoneContacts()).thenReturn(ArrayList())
    }

    private fun givenRequestUnsuccessful() {
        whenever(
                marvelRepository.getCharacters(
                        any(),
                        any()
                )
        ).thenReturn(Single.error(Throwable("marvel not cool")))
    }
}