package com.rubenexposito.contactsmarvelapp.data.contacts

import android.Manifest
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.domain.model.ContactMapper
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class ContactsManager(private val activity: AppCompatActivity, private val mapper: ContactMapper) :
    LoaderManager.LoaderCallbacks<Cursor> {
    var contacts: List<Contact> = ArrayList()

    init {
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.READ_CONTACTS)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    handlePermissionGranted()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    handlePermissionRationale(token)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    handlePermissionDenied()
                }

            }).check()
    }

    private fun handlePermissionGranted() {
        LoaderManager.getInstance(activity).initLoader(CONTACTS_ID_LOADER, null, this)
    }

    private fun handlePermissionRationale(token: PermissionToken?) {
        activity.alert(R.string.permissions_read_contacts_rationale, R.string.permissions_read_contacts_title) {
            yesButton { dialog ->
                dialog.dismiss()
                token?.continuePermissionRequest()
            }
            noButton { dialog ->
                dialog.dismiss()
                token?.cancelPermissionRequest()
            }
            onCancelled {
                token?.cancelPermissionRequest()
            }
        }.show()
    }

    private fun handlePermissionDenied() {
        activity.toast(R.string.permissions_read_contacts_denied)
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

        return CursorLoader(activity, contactsUri, projection, null, null, null)
    }

    companion object {
        private const val CONTACTS_ID_LOADER = 1
    }
}