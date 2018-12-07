package com.rubenexposito.contactsmarvelapp.presentation.common

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.common.inflate
import com.rubenexposito.contactsmarvelapp.common.load
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import kotlinx.android.synthetic.main.item_contact_selected.view.*

class SelectedContactsAdapter(private val callback: ContactListener, var contacts: MutableList<Contact> = ArrayList()) :
    RecyclerView.Adapter<SelectedContactViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SelectedContactViewHolder(parent.inflate(R.layout.item_contact_selected))

    override fun getItemCount(): Int = contacts.size
    override fun getItemId(position: Int): Long = contacts[position].hashCode().toLong()
    override fun onBindViewHolder(holder: SelectedContactViewHolder, position: Int) = with(holder) {
        val contact = contacts[position]
        bind(contact)
        itemView.ivClear.setOnClickListener { callback.onContactSelected(contact) }
    }

    fun addOrRemoveContact(contact: Contact): Int {
        val indexOf = contacts.indexOf(contact)
        if (contacts.contains(contact)) {
            contacts.remove(contact)
        } else {
            contacts.add(0, contact)
        }

        return indexOf
    }


}

class SelectedContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(contact: Contact) = with(itemView) {
        ivAvatar.load(contact.avatar, contact.name)
        tvName.text = contact.name
    }
}