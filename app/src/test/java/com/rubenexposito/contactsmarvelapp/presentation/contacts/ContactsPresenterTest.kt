package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.rubenexposito.contactsmarvelapp.domain.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ContactsPresenterTest {

    @Mock
    lateinit var view: ContactsContract.View

    @Mock
    lateinit var useCase: GetContactsUseCase

    private val presenter by lazy {
        ContactsPresenter(view, useCase)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should show loading when request contacts`() {
        presenter.onCreate()

        verify(view).showLoading()
    }

    @Test
    fun `should request data when request contacts`() {
        presenter.onCreate()

        verify(useCase).execute(any(), any())
    }

    @Test
    fun `should clear use case when on pause`() {
        presenter.onPause()

        verify(useCase).clear()
    }

    @Test
    fun `should show contacts when response successful`() {
        val contacts = givenContacts()
        presenter.onComplete(contacts)

        verify(view).showContacts(contacts)
        verify(view).hideLoading()
    }

    @Test
    fun `should show error when given error response`() {
        presenter.onError(Throwable())

        verify(view).showError(any())
        verify(view).hideLoading()
    }

    private fun givenContacts() : List<Contact> = listOf(Contact("", ""))

}