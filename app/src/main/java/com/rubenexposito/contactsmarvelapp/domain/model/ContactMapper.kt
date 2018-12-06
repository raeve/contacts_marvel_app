package com.rubenexposito.contactsmarvelapp.domain.model

import android.database.Cursor
import android.provider.ContactsContract
import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse

class ContactMapper {
    fun mapMarvel(response: GetCharactersResponse): MutableList<Contact> =
        response.data.results.map {
            Contact(it.name, it.thumbnail.path + "." + it.thumbnail.extension)
        }.toMutableList()

    fun mapCursor(cursor: Cursor?): MutableList<Contact> {
        val contacts: MutableList<Contact> = ArrayList()

        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val contact = Contact(
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
                    )
                    contacts.add(contact)
                } while (cursor.moveToNext())
            }
        }

        return contacts
    }
}