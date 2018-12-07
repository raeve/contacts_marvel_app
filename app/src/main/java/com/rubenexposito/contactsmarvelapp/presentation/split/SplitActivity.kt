package com.rubenexposito.contactsmarvelapp.presentation.split

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class SplitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split)
    }

    companion object {
        private const val KEY_CONTACTS = "key:contacts"
        private const val KEY_AMOUNT = "key:amount"

        fun getIntent(context: Context, contacts: ArrayList<Contact>, amount: Double): Intent =
            Intent(context, SplitActivity::class.java).run {
                putParcelableArrayListExtra(KEY_CONTACTS, contacts)
                putExtra(KEY_AMOUNT, amount)
            }
    }
}
