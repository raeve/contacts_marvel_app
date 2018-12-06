package com.rubenexposito.contactsmarvelapp.presentation.contacts

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class ContactsFragment : Fragment(),  ContactsContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun showContacts(contacts: List<Contact>) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(@StringRes stringRes: Int) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ContactsFragment()
    }
}