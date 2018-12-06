package com.rubenexposito.contactsmarvelapp.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rubenexposito.contactsmarvelapp.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
