package com.rubenexposito.contactsmarvelapp.presentation.contacts

import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class ContactsPresenter(private val view: ContactsContract.View, private val useCase: GetContactsUseCase) : ContactsContract.Presenter {

    override fun onCreate() {
        view.showLoading()
        useCase.execute(::onComplete, ::onError)
    }

    override fun onPause() = useCase.clear()

    internal fun onComplete(contacts: List<Contact>) {
        view.showContacts(contacts)
        view.hideLoading()
    }

    internal fun onError(throwable: Throwable) {
        view.showError(R.string.error_could_not_load_contacts)
        view.hideLoading()
    }
}