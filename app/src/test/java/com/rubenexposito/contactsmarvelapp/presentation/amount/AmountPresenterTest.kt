package com.rubenexposito.contactsmarvelapp.presentation.amount

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.rubenexposito.contactsmarvelapp.Navigator
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AmountPresenterTest {

    @Mock
    lateinit var view: AmountContract.View

    @Mock
    lateinit var navigator: Navigator

    private val presenter by lazy {
        AmountPresenter(view, navigator)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should update decimals when decimals are activated on number select`() {
        givenDecimalActivated()
        presenter.onNumberSelected("1")
        verify(view).updateDecimals(any(), any())
    }

    @Test
    fun `should update amount on number select`() {
        presenter.onNumberSelected("1")
        verify(view).updateAmount(any())
        verify(view).enableSplit(any())
    }

    @Test
    fun `should enable split when amount is not zero on number select`() {
        givenAmount("1")
        presenter.onNumberSelected("1")
        verify(view).enableSplit(any())
    }

    @Test
    fun `should disable split when amount is zero on number select being zero`() {
        presenter.onNumberSelected("0")

        verify(view).disableSplit()
    }

    @Test
    fun `should update decimals when amount is not 1000 on dot select`() {
        presenter.onDotSelected()
        verify(view).updateDecimals(any(), any())
    }

    @Test
    fun `should not update decimals when amount is 1000 on dot select`() {
        givenAmount(AmountPresenter.MAX)
        presenter.onDotSelected()
        verifyZeroInteractions(view)
    }

    @Test
    fun `should update decimals when decimals activated on backspace select`() {
        givenDecimalActivated()
        presenter.onBackspaceSelected()
        verify(view).updateDecimals(any(), any())
    }

    @Test
    fun `should update amount when amount is not zero on backspace select`() {
        givenAmount("1")
        presenter.onBackspaceSelected()
        verify(view).updateAmount(any())
    }

    @Test
    fun `should not update amount when amount is zero on backspace select`() {
        givenAmount("0")
        givenDecimals("")
        presenter.onBackspaceSelected()
        verify(view).disableSplit()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `should update split when amount is zero on backspace select`() {
        presenter.onBackspaceSelected()
        verify(view).disableSplit()
    }

    @Test
    fun `should update split when amount is more or equals than ten on backspace select`() {
        givenAmount("10")
        presenter.onBackspaceSelected()
        verify(view).enableSplit(any())
    }

    @Test
    fun `should disable split when amount is less than ten on backspace select`() {
        givenAmount("9")
        presenter.onBackspaceSelected()
        verify(view).disableSplit()
    }

    @Test
    fun `should navigate to split screen on button click`() {
        var amount = "100"
        givenAmount(amount)
        givenEmptyContacts()

        presenter.onSplitClicked()
        verify(navigator).showSplit(ArrayList(), amount.toDouble())
    }

    private fun givenAmount(amount: String) {
        presenter.amount = amount
    }

    private fun givenDecimals(decimals: String) {
        presenter.decimals = decimals
    }

    private fun givenEmptyContacts() {
        presenter.contacts = ArrayList()
    }

    private fun givenDecimalActivated() {
        presenter.isDecimalActivated = true
    }
}