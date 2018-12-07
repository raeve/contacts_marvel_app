package com.rubenexposito.contactsmarvelapp.presentation.amount

import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.common.removeLatestChar
import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class AmountPresenter(private val view: AmountContract.View, private val navigator: Navigator) : AmountContract.Presenter {

    private lateinit var contacts: List<Contact>

    private var isDecimalActivated = false
    private var amount = ZERO
    private var decimals = EMPTY

    override fun updateContacts(contacts: List<Contact>) {
        this.contacts = contacts
    }

    override fun onNumberSelected(number: String) {
        if(isDecimalActivated || number != ZERO) {
            updateAmount(number)
        }
    }

    override fun onDotSelected() {
        isDecimalActivated = true
    }

    override fun onBackspaceSelected() {
        if(isDecimalActivated && decimals.isNotEmpty()) {
            decimals.removeLatestChar()
        } else if (!isDecimalActivated && amount != ZERO) {
            amount.removeLatestChar()
            if(amount.isEmpty()) {
                amount = ZERO
            }
        }

        view.updateAmount(amount, decimals)
    }

    override fun onSplitClicked() {
        if(amount != ZERO) {
            var total = amount.toDouble()
            if(decimals.isNotEmpty()){
                total += "0.$decimals".toDouble()
            }

            navigator.showSplit(contacts as ArrayList<Contact>, total)
        }
    }

    private fun updateAmount(number: String) {
        if(isDecimalActivated && decimals.length < 2) {
            decimals += number
        }else if(amount.length < 3 || amount.length < 4 && amount == HUNDRED && decimals.isEmpty() && number == ONE){
            amount = number + amount
        }

        view.updateAmount(amount, decimals)
    }

    companion object {
        const val EMPTY = ""
        const val ZERO = "0"
        const val ONE = "1"
        const val HUNDRED = "100"

    }
}