package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.interactor.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import retrofit2.HttpException

class ContactsPresenter(
        private val view: ContactsContract.View,
        private val useCase: GetContactsUseCase,
        private val navigator: Navigator
) : ContactsContract.Presenter {
    var offset = 0

    override fun onPause() = useCase.clear()

    override fun onSplitBetweenClicked(contacts: MutableList<Contact>) = navigator.showAmount(contacts as ArrayList<Contact>)

    override fun onContactSelected(contact: Contact) {
        contact.selected = !contact.selected
        view.addOrRemoveContact(contact)
    }

    override fun loadContacts(reset: Boolean) {
        view.showLoading()
        if (reset) offset = 0
        useCase.execute(::onComplete, ::onError, offset, LIMIT)
    }

    internal fun onComplete(contacts: MutableList<Contact>) {
        view.showContacts(contacts)
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

    companion object {
        const val LIMIT = 50
    }
}