package com.rubenexposito.contactsmarvelapp.data

import com.rubenexposito.contactsmarvelapp.data.contacts.ContactsManager
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import io.reactivex.Single

interface ContactsRepository {
    fun getContacts(): Single<MutableList<Contact>>
}

class ContactsRepositoryImpl(private val contactsManager: ContactsManager) : ContactsRepository {
    override fun getContacts(): Single<MutableList<Contact>> = Single.just(contactsManager.contacts)
}