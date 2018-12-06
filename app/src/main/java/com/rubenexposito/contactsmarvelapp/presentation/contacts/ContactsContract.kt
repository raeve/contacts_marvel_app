package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.presentation.BaseView

interface ContactsContract {
    interface View : BaseView {
        fun showContacts(contacts: List<Contact>)
    }

    interface Presenter {
        fun onCreate()
        fun onPause()
    }
}