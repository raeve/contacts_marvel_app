package com.rubenexposito.contactsmarvelapp.presentation.amount

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.rubenexposito.contactsmarvelapp.R

class AmountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_amount)
    }

    companion object {
        const val INTENT_KEY_CONTACTS = "key:contacts"
    }
}