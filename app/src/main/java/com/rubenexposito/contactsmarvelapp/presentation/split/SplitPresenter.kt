package com.rubenexposito.contactsmarvelapp.presentation.split

import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class SplitPresenter(private val view: SplitContract.View) : SplitContract.Presenter {

    override fun updateAmount(amount: Double) {
        view.updateAmount(amount)
    }

    override fun updateContacts(contacts: List<Contact>) {
        view.updateContacts(contacts)
    }
}