package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.presentation.BaseView
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.ContactListener

interface ContactsContract {
    interface View : BaseView {
        fun showContacts(contacts: List<Contact>)
    }

    interface Presenter : ContactListener {
        fun onCreate()
        fun onPause()
    }
}