package com.rubenexposito.contactsmarvelapp.presentation.amount

import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.common.removeLatestChar
import com.rubenexposito.contactsmarvelapp.common.toDecimals
import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class AmountPresenter(private val view: AmountContract.View, private val navigator: Navigator) :
        AmountContract.Presenter {

    private lateinit var contacts: List<Contact>

    private var isDecimalActivated = false
    private var amount = ZERO
    private var decimals = ""

    override fun updateContacts(contacts: List<Contact>) {
        this.contacts = contacts
    }

    override fun onNumberSelected(number: String) {
        if (isDecimalActivated) {
            updateDecimals(number)
        } else {
            updateAmount(number)
        }
    }

    override fun onDotSelected() {
        if (amount != MAX) {
            isDecimalActivated = true

            view.updateDecimals(decimals.toDecimals(), isDecimalActivated)
        }
    }

    override fun onBackspaceSelected() {
        if (isDecimalActivated) {
            if (decimals.isNotEmpty()) decimals = decimals.removeLatestChar()
            if (decimals.isEmpty()) isDecimalActivated = false

            view.updateDecimals(decimals.toDecimals(), isDecimalActivated)
        } else if (!isDecimalActivated && amount != ZERO) {
            amount = amount.removeLatestChar()
            if (amount.isEmpty()) {
                amount = ZERO
            }
            view.updateAmount(amount)
        }

        updateSplit()
    }

    override fun onSplitClicked() {
        if (amount != ZERO) {
            var total = amount.toDouble()
            if (decimals.isNotEmpty()) {
                total += "0.${decimals.toDecimals()}".toDouble()
            }

            for(contact in contacts) contact.split = total/contacts.size

            navigator.showSplit(contacts as ArrayList<Contact>, total)
        }
    }

    private fun updateDecimals(number: String) {
        if (decimals.length < 2) {
            decimals += number
        }

        view.updateDecimals(decimals.toDecimals(), isDecimalActivated)
        updateSplit()
    }

    private fun updateAmount(number: String) {
        if (amount.length < 3 || (amount.length < 4 && amount == HUNDRED && decimals.isEmpty() && number == ZERO)) {
            amount = if (amount == ZERO) number else amount + number
        }

        view.updateAmount(amount)
        updateSplit()
    }

    private fun updateSplit() {
        val split = "€$amount.${decimals.toDecimals()}"
        if (split == SPLIT_EMPTY) view.disableSplit() else view.enableSplit(split)
    }

    companion object {
        const val ZERO = "0"
        const val HUNDRED = "100"
        const val MAX = "1000"
        const val SPLIT_EMPTY = "€0.00"

    }
}