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
import com.rubenexposito.contactsmarvelapp.presentation.common.ContactsAdapter
import com.rubenexposito.contactsmarvelapp.presentation.common.SelectedContactsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.divider_layout.*
import kotlinx.android.synthetic.main.layout_button_split.*
import kotlinx.android.synthetic.main.layout_empty.*
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
        presenter.loadContacts(true)
    }

    override fun onResume() {
        super.onResume()
        setTitle(R.string.contacts_title)
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    private fun initView() {
        srlContacts.setOnRefreshListener { presenter.loadContacts(true) }

        with(rvContacts) {
            adapter = contactsAdapter
            val linearLayoutManager = LinearLayoutManager(this@ContactsActivity)
            layoutManager = linearLayoutManager
            //TODO: Review pagination
//            addOnScrollListener(object: RecyclerView.OnScrollListener() {
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 1){
//                        presenter.loadContacts(false)
//                    }
//                    super.onScrollStateChanged(recyclerView, newState)
//                }
//            })
            setHasFixedSize(true)
        }
        with(rvSelectedContacts) {
            adapter = selectedContactsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        btnSplit.setOnClickListener { presenter.onSplitBetweenClicked(selectedContactsAdapter.contacts) }
    }

    override fun addContacts(contacts: MutableList<Contact>) {
        rvContacts.show()
        with(rvContacts.adapter as ContactsAdapter) {
            selectedContacts = selectedContactsAdapter.contacts
            val position = this.contacts.size
            this.addContacts(contacts)
            notifyItemRangeInserted(position, contacts.size)
        }
    }

    override fun showContacts(contacts: MutableList<Contact>) {
        rvContacts.show()
        with(rvContacts.adapter as ContactsAdapter) {
            selectedContacts = selectedContactsAdapter.contacts
            this.contacts = contacts
            notifyDataSetChanged()
        }

        showSelectedContacts()
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
        }

        showSelectedContacts()

        with(contactsAdapter) {
            selectedContacts = selectedContactsAdapter.contacts
            val position = updateContact(contact)
            notifyItemChanged(position)
        }

        btnSplit.text = getString(R.string.button_split_between, selectedContactsAdapter.itemCount)
        if (selectedContactsAdapter.itemCount > 0) btnSplit.show() else btnSplit.hide()
    }

    fun showSelectedContacts() {
        if (selectedContactsAdapter.itemCount > 0) {
            rvSelectedContacts.show()
            divider.show()
        } else {
            rvSelectedContacts.hide()
            divider.hide()
        }
    }

    override fun showLoading() {
        srlContacts.isRefreshing = false
        viewEmpty.hide()
        progressView.show()
    }

    override fun hideLoading() {
        srlContacts.isRefreshing = false
        progressView.hide()
    }

    override fun showError(@StringRes stringRes: Int) {
        if (contactsAdapter.itemCount == 0) {
            rvSelectedContacts.hide()
            rvContacts.hide()
            viewEmpty.show()
        }
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
}