package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.rubenexposito.contactsmarvelapp.Navigator
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
    lateinit var navigator: Navigator

    @Mock
    lateinit var useCase: GetContactsUseCase

    private val presenter by lazy {
        ContactsPresenter(view, useCase, navigator)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should show loading after load contacts from phone`() {
        presenter.loadContacts(false)
        verify(view).showLoading()
    }

    @Test
    fun `should request data after load contacts from phone`() {
        presenter.loadContacts(false)
        verify(useCase).execute(any(), any(), any(), any())
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

    @Test
    fun `should navigate to amount view when click split`() {
        val contacts = givenContacts()

        presenter.onSplitBetweenClicked(contacts)
        verify(navigator).showAmount(contacts as ArrayList<Contact>)
    }

    private fun givenContacts(): MutableList<Contact> = listOf(Contact("", "")).toMutableList()

}