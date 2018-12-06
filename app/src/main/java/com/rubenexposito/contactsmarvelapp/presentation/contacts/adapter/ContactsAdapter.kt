package com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.common.hide
import com.rubenexposito.contactsmarvelapp.common.inflate
import com.rubenexposito.contactsmarvelapp.common.load
import com.rubenexposito.contactsmarvelapp.common.show
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsAdapter(private val callback: ContactListener, var contacts: MutableList<Contact> = ArrayList()) :
    RecyclerView.Adapter<ContactViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(parent.inflate(R.layout.item_contact))

    override fun getItemCount(): Int = contacts.size
    override fun getItemId(position: Int): Long = contacts[position].name.hashCode().toLong()
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = with(holder) {
        val contact = contacts[position]
        bind(contact)
        itemView.setOnClickListener { callback.onContactSelected(contact) }
    }

    fun addContacts(contacts: MutableList<Contact>) {
        this.contacts.addAll(contacts)
        this.contacts = this.contacts.sortedWith(compareBy { it.name }).toMutableList()
    }

    fun updateContact(contact: Contact): Int {
        val indexOf = contacts.indexOf(contact)
        if (contacts.contains(contact)) {
            contacts[indexOf] = contact
        }

        return indexOf
    }
}

interface ContactListener {
    fun onContactSelected(contact: Contact)
}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(contact: Contact) = with(itemView) {
        ivAvatar.load(contact.avatar, contact.name)
        tvName.text = contact.name
        tvPhone.text = contact.phone
        if (tvPhone.text.isNotBlank()) tvPhone.show() else tvPhone.hide()
        if (contact.selected) ivSelected.show() else ivSelected.hide()
    }
}