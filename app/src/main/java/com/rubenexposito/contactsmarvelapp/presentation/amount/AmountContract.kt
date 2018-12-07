package com.rubenexposito.contactsmarvelapp.presentation.amount

import com.rubenexposito.contactsmarvelapp.domain.model.Contact

interface AmountContract {
    enum class Keys {
        ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,DOT,ZERO,BACKSPACE
    }
    interface View {
        fun updateAmount(amount: String)

        fun updateDecimals(decimals: String, visible: Boolean)

        fun enableSplit(bill: String)

        fun disableSplit()
    }

    interface Presenter {
        fun updateContacts(contacts: List<Contact>)

        fun onNumberSelected(number: String)

        fun onDotSelected()

        fun onBackspaceSelected()

        fun onSplitClicked()
    }
}