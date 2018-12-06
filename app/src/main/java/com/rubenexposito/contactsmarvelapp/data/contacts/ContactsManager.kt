package com.rubenexposito.contactsmarvelapp.data.contacts

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.domain.model.ContactMapper
import java.util.*

class ContactsManager(private val context: AppCompatActivity, private val mapper: ContactMapper) :
    LoaderManager.LoaderCallbacks<Cursor> {
    var contacts: List<Contact> = ArrayList()

    init {
        LoaderManager.getInstance(context).initLoader(CONTACTS_ID_LOADER, null, this)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        return contactsLoader()
    }

    override fun onLoadFinished(p0: Loader<Cursor>, p1: Cursor?) {
        contacts = mapper.mapCursor(p1)
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {}

    private fun contactsLoader(): Loader<Cursor> {
        val contactsUri = ContactsContract.Contacts.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
        )

        return CursorLoader(context, contactsUri, projection, null, null, null)
    }

    companion object {
        private const val CONTACTS_ID_LOADER = 1
    }
}