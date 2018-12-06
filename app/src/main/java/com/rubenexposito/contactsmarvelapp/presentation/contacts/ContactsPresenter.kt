package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import retrofit2.HttpException

class ContactsPresenter(private val view: ContactsContract.View, private val useCase: GetContactsUseCase) :
    ContactsContract.Presenter {

    override fun onCreate() {
        view.showLoading()
        view.resetContacts()
        useCase.execute(::onComplete, ::onError)
    }

    override fun onPause() = useCase.clear()

    override fun onContactSelected(contact: Contact) {
        //TODO: Manage contacts selected.
    }

    internal fun onComplete(contacts: MutableList<Contact>) {
        view.addContacts(contacts)
        view.hideLoading()
    }

    internal fun onError(throwable: Throwable) {
        if(throwable is HttpException) {
            view.showError(R.string.error_could_not_retrieve_marvel_characters)
        }else{
            view.showError(R.string.error_could_not_load_contacts)
        }
        view.hideLoading()
    }
}