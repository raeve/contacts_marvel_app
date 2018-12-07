package com.rubenexposito.contactsmarvelapp.presentation.split

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.common.show
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.ContactsAdapter
import kotlinx.android.synthetic.main.activity_split.*
import kotlinx.android.synthetic.main.item_contact.*
import kotlinx.android.synthetic.main.layout_button_split.*
import javax.inject.Inject

class SplitActivity : AppCompatActivity(), SplitContract.View {

    @Inject
    lateinit var presenter: SplitContract.Presenter

    @Inject
    lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split)
        initExtra()
        initView()
    }

    override fun updateAmount(amount: Double) {
        val text = "€$amount"
        tvName.text = text
    }

    override fun updateContacts(contacts: List<Contact>) {
        contactsAdapter.contacts = contacts as MutableList<Contact>
        contactsAdapter.notifyDataSetChanged()
    }

    private fun initExtra() {
        intent?.let {
            presenter.updateAmount(intent.getDoubleExtra(KEY_AMOUNT, 0.00))
            presenter.updateContacts(intent.getParcelableArrayListExtra(KEY_CONTACTS))
        }
    }

    private fun initView() {
        ivAvatar.setImageDrawable(getDrawable(R.drawable.ic_call_split_24dp))

        with(rvContacts) {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        btnSplit.show()
        btnSplit.setOnClickListener { presenter.onSplitClicked() }
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
