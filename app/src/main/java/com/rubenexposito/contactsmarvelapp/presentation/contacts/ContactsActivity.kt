package com.rubenexposito.contactsmarvelapp.presentation.contacts

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.common.hide
import com.rubenexposito.contactsmarvelapp.common.show
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.ContactsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.layout_loading.*
import javax.inject.Inject

class ContactsActivity : AppCompatActivity(), ContactsContract.View {
    @Inject
    lateinit var presenter: ContactsContract.Presenter

    @Inject
    lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        AndroidInjection.inject(this)
        initView()
    }

    override fun onResume() {
        super.onResume()
        presenter.onCreate()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    private fun initView() {
        with(rvContacts) {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun resetContacts() {
        rvContacts.hide()
        contactsAdapter.contacts = ArrayList()
        contactsAdapter.notifyDataSetChanged()
    }

    override fun addContacts(contacts: MutableList<Contact>) {
        rvContacts.show()
        contactsAdapter.addContacts(contacts)
        contactsAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressView.show()
    }

    override fun hideLoading() {
        progressView.hide()
    }

    override fun showError(@StringRes stringRes: Int) {
        if (contactsAdapter.itemCount == 0) rvContacts.hide()
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
}