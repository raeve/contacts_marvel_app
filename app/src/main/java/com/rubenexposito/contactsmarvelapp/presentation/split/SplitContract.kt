package com.rubenexposito.contactsmarvelapp.presentation.split

import com.rubenexposito.contactsmarvelapp.domain.model.Contact

interface SplitContract {
    interface View {
        fun updateAmount(amount: Double)
        fun updateContacts(contacts: List<Contact>)
    }

    interface Presenter {
        fun updateAmount(amount: Double)
        fun updateContacts(contacts: List<Contact>)
    }
}