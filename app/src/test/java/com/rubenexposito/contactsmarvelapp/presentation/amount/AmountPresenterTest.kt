package com.rubenexposito.contactsmarvelapp.presentation.amount

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
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
    fun `should update decimals when number selected is not zero and decimals are activated`() {
        givenDecimalActivated()
        presenter.onNumberSelected("1")
        verify(view).updateDecimals(any(), any())
    }

    @Test
    fun `should enable split on amount not zero when number selected`() {
        givenNotZeroAmount()
        presenter.onNumberSelected("1")
        verify(view).enableSplit(any())
    }

    @Test
    fun `should disable split on amount zero when number selected is zero`() {
        presenter.onNumberSelected("0")

        verify(view).disableSplit()
    }

    @Test
    fun `should update amount when number is selected`() {
        presenter.onNumberSelected("1")
        verify(view).updateAmount(any())
        verify(view).enableSplit(any())
    }

    @Test
    fun `should update decimals when dot is selected`() {
        presenter.onDotSelected()
        verify(view).updateDecimals(any(), any())
    }

    @Test
    fun `should update decimals when decimals activated on backspace select`() {
        givenDecimalActivated()
        presenter.onBackspaceSelected()
        verify(view).updateDecimals(any(), any())
    }

    @Test
    fun `should update amount when amount is not zero on backspace select`() {
        givenNotZeroAmount()
        presenter.onBackspaceSelected()
        verify(view).updateAmount(any())
    }

    @Test
    fun `should update split when amount is zero on backspace select`() {
        presenter.onBackspaceSelected()
        verify(view).disableSplit()
    }

    @Test
    fun `should update split when amount is not zero on backspace select`() {
        givenNotZeroAmount()
        presenter.onBackspaceSelected()
        verify(view).enableSplit(any())
    }

    @Test
    fun `should not update decimals when amount is 1000 on dot select`() {
        givenMaxAmount()
        presenter.onDotSelected()
        verifyZeroInteractions(view)
    }

    @Test
    fun `should navigate to split screen on button click`() {
        givenNotZeroAmount()
        givenEmptyContacts()

        presenter.onSplitClicked()
        verify(navigator).showSplit(ArrayList(), 100.00)
    }

    private fun givenNotZeroAmount() {
        presenter.amount = AmountPresenter.HUNDRED
    }

    private fun givenMaxAmount() {
        presenter.amount = AmountPresenter.MAX
    }

    private fun givenEmptyContacts() {
        presenter.contacts = ArrayList()
    }

    private fun givenDecimalActivated() {
        presenter.isDecimalActivated = true
    }

    private fun givenNotEmptyDecimals() {
        presenter.decimals = "10"
    }
}