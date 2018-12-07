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
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.SelectedContactsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.divider_layout.*
import kotlinx.android.synthetic.main.layout_button_split.*
import kotlinx.android.synthetic.main.layout_loading.*
import javax.inject.Inject

class ContactsActivity : AppCompatActivity(), ContactsContract.View {
    @Inject
    lateinit var presenter: ContactsContract.Presenter

    @Inject
    lateinit var contactsAdapter: ContactsAdapter

    @Inject
    lateinit var selectedContactsAdapter: SelectedContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        AndroidInjection.inject(this)
        initView()
        presenter.onCreate()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    private fun initView() {
        srlContacts.setOnRefreshListener { presenter.onCreate() }

        with(rvContacts) {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        with(rvSelectedContacts) {
            adapter = selectedContactsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        btnSplit.setOnClickListener { presenter.onSplitBetweenClicked(selectedContactsAdapter.contacts) }
    }

    override fun resetContacts() {
        rvContacts.hide()
        contactsAdapter.contacts = ArrayList()
        contactsAdapter.notifyDataSetChanged()
    }

    override fun addContacts(contacts: MutableList<Contact>) {
        rvContacts.show()
        contactsAdapter.selectedContacts = selectedContactsAdapter.contacts
        contactsAdapter.contacts = contacts
        contactsAdapter.notifyDataSetChanged()
    }

    override fun addOrRemoveContact(contact: Contact) {
        with(selectedContactsAdapter) {
            val position = addOrRemoveContact(contact)
            if (position == -1) {
                notifyItemInserted(0)
                rvSelectedContacts.scrollToPosition(0)
            } else {
                notifyItemRemoved(position)
            }

            if (itemCount > 0) {
                rvSelectedContacts.show()
                divider.show()
            } else {
                rvSelectedContacts.hide()
                divider.hide()
            }
        }

        with(contactsAdapter) {
            selectedContacts = selectedContactsAdapter.contacts
            val position = updateContact(contact)
            notifyItemChanged(position)
        }

        btnSplit.text = getString(R.string.button_split_between, selectedContactsAdapter.itemCount)
        if (selectedContactsAdapter.itemCount > 0) btnSplit.show() else btnSplit.hide()
    }

    override fun showLoading() {
        srlContacts.isRefreshing = false
        progressView.show()
    }

    override fun hideLoading() {
        srlContacts.isRefreshing = false
        progressView.hide()
    }

    override fun showError(@StringRes stringRes: Int) {
        if (contactsAdapter.itemCount == 0) rvContacts.hide()
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
}