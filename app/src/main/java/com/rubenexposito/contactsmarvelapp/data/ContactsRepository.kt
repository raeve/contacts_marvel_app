package com.rubenexposito.contactsmarvelapp.data

import android.Manifest
import android.database.Cursor
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

interface ContactsRepository {
    fun getPhoneContacts(): MutableList<Contact>
}

class ContactsRepositoryImpl(
    private val activity: AppCompatActivity
) : ContactsRepository {
    override fun getPhoneContacts(): MutableList<Contact> = mapCursor()

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

    }

    private fun handlePermissionRationale(token: PermissionToken?) {
        activity.alert(
            R.string.permissions_read_contacts_rationale,
            R.string.permissions_read_contacts_title
        ) {
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

    private fun mapCursor(): MutableList<Contact> {
        val contacts: MutableList<Contact> = ArrayList()

        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.PHOTO_URI,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )

        val cursor: Cursor? = activity.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val photo =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
                    var phoneNumber: String? = null
                    if (cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER) > 0) {
                        val phoneCursor: Cursor? = activity.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?",
                            arrayOf(id),
                            null
                        )

                        phoneCursor?.let {
                            while (it.moveToNext()) {
                                if (phoneNumber.isNullOrEmpty())
                                    phoneNumber =
                                            it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            }
                            it.close()
                        }
                    }

                    if (!phoneNumber.isNullOrBlank()) contacts.add(
                        Contact(
                            "phone:$id",
                            name,
                            photo,
                            phoneNumber
                        )
                    )


                } while (cursor.moveToNext())
            }
            cursor.close()
        }


        return contacts
    }
}