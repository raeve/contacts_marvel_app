package com.rubenexposito.contactsmarvelapp

import android.app.Activity
import android.content.Intent
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountActivity

interface Navigator {
    fun showAmount(contacts: ArrayList<Contact>)
    fun showSplit(contacts: ArrayList<Contact>, total: Double)
}

class NavigatorImpl(private val activity: Activity) : Navigator {
    override fun showAmount(contacts: ArrayList<Contact>) {
        val intent = Intent(activity, AmountActivity::class.java)
        intent.putParcelableArrayListExtra(AmountActivity.INTENT_KEY_CONTACTS, contacts)
        activity.startActivity(intent)
    }

    override fun showSplit(contacts: ArrayList<Contact>, total: Double) {
        //TODO: Navigate to split screen
    }
}