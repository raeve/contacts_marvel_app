package com.rubenexposito.contactsmarvelapp.presentation.split

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SplitPresenterTest {

    @Mock
    lateinit var view: SplitContract.View

    private val presenter by lazy {
        SplitPresenter(view)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `update amount when received from intent`() {
        presenter.updateAmount(any())
        verify(view).updateAmount(any())
    }

    @Test
    fun `update contacts when receive them from intent`() {
        presenter.updateContacts(emptyList())
        verify(view).updateContacts(emptyList())
    }

}