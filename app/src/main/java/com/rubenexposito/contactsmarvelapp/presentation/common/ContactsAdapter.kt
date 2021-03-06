package com.rubenexposito.contactsmarvelapp.presentation.common

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.common.*
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsAdapter(
    private val callback: ContactListener? = null
) :
    RecyclerView.Adapter<ContactViewHolder>() {
    var contacts: MutableList<Contact> = ArrayList()
    var selectedContacts: List<Contact> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(parent.inflate(R.layout.item_contact))

    override fun getItemCount(): Int = contacts.size
    override fun getItemId(position: Int): Long = contacts[position].hashCode().toLong()
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = with(holder) {
        val contact = contacts[position]
        bind(contact, selectedContacts)
        itemView.setOnClickListener { callback?.onContactSelected(contact) }
    }

    fun updateContact(contact: Contact): Int {
        val indexOf = contacts.indexOf(contact)
        if (contacts.contains(contact)) {
            contacts[indexOf] = contact
        }

        return indexOf
    }

    fun addContacts(contacts: MutableList<Contact>) {
        this.contacts.addAll(contacts)
        this.contacts.sortBy { it.name }
    }
}

interface ContactListener {
    fun onContactSelected(contact: Contact)
}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(contact: Contact, selectedContacts: List<Contact>) = with(itemView) {
        ivAvatar.load(contact.avatar, contact.name)
        tvName.text = contact.name
        tvPhone.text = contact.phone
        tvSplit.text = contact.split.toPrice()
        if (tvPhone.text.isNotBlank()) tvPhone.show() else tvPhone.hide()
        if (contact.split != 0.0) tvSplit.show() else tvSplit.hide()
        if (selectedContacts.contains(contact)) ivSelected.show() else ivSelected.hide()
    }
}