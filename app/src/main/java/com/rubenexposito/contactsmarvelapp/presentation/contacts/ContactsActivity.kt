package com.rubenexposito.contactsmarvelapp.presentation.contacts

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import dagger.android.AndroidInjection
import javax.inject.Inject

class ContactsActivity : AppCompatActivity(), ContactsContract.View {
    @Inject
    lateinit var presenter: ContactsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        AndroidInjection.inject(this)
        presenter.onCreate()
    }

    override fun showContacts(contacts: List<Contact>) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(@StringRes stringRes: Int) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
}