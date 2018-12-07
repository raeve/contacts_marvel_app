package com.rubenexposito.contactsmarvelapp

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountActivity

interface Navigator {
    fun showAmount(contacts: ArrayList<Contact>)
    fun showSplit(contacts: ArrayList<Contact>, total: Double)
}

class NavigatorImpl(private val activity: Activity) : Navigator {
    override fun showAmount(contacts: ArrayList<Contact>) = activity.startActivity(AmountActivity.getIntent(activity, contacts))

    override fun showSplit(contacts: ArrayList<Contact>, total: Double) {
        //TODO: Navigate to split screen
    }
}