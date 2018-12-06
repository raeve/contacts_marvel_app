package com.rubenexposito.contactsmarvelapp.presentation

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsFragment

interface Navigator {
    fun showContacts()
}

class NavigatorImpl(private val activity: AppCompatActivity) : Navigator {
    override fun showContacts() {
        replaceFragment(ContactsFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, fragment::class.java.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}