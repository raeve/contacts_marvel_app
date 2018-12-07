package com.rubenexposito.contactsmarvelapp.domain

import com.rubenexposito.contactsmarvelapp.domain.model.Contact

interface ContactsRepository {
    fun getPhoneContacts(): MutableList<Contact>
}