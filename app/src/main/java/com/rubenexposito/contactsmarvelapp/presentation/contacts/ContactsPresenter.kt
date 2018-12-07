package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import retrofit2.HttpException

class ContactsPresenter(
    private val view: ContactsContract.View,
    private val useCase: GetContactsUseCase,
    private val navigator: Navigator
) : ContactsContract.Presenter {

    override fun onCreate() {
        view.showLoading()
        useCase.execute(::onComplete, ::onError)
    }

    override fun onPause() = useCase.clear()

    override fun onSplitBetweenClicked(contacts: MutableList<Contact>) {
        navigator.showAmount(contacts as ArrayList<Contact>)
    }

    override fun onContactSelected(contact: Contact) {
        contact.selected = !contact.selected
        view.addOrRemoveContact(contact)
    }

    internal fun onComplete(marvelContacts: MutableList<Contact>) {
        val contacts = marvelContacts.sortedWith(compareBy { it.name }).toMutableList()
        view.addContacts(contacts)
        view.hideLoading()
    }

    internal fun onError(throwable: Throwable) {
        if (throwable is HttpException) {
            view.showError(R.string.error_could_not_retrieve_marvel_characters)
        } else {
            view.showError(R.string.error_could_not_load_contacts)
        }
        view.hideLoading()
    }
}