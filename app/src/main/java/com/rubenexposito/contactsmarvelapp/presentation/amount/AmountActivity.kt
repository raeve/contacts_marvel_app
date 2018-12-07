package com.rubenexposito.contactsmarvelapp.presentation.amount

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.rubenexposito.contactsmarvelapp.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class AmountActivity : AppCompatActivity(), AmountContract.View {
    @Inject
    lateinit var presenter: AmountContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_amount)
        AndroidInjection.inject(this)
        initExtras()
    }

    private fun initExtras() {
        intent?.let {
            presenter.updateContacts(intent.getParcelableArrayListExtra(INTENT_KEY_CONTACTS))
        }
    }

    override fun updateAmount(amount: String, decimals: String) {
        //TODO Update UI
    }

    companion object {
        const val INTENT_KEY_CONTACTS = "key:contacts"
    }
}