package com.rubenexposito.contactsmarvelapp.presentation.amount

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rubenexposito.contactsmarvelapp.R
import com.rubenexposito.contactsmarvelapp.common.hide
import com.rubenexposito.contactsmarvelapp.common.show
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_amount.*
import kotlinx.android.synthetic.main.layout_button_split.*
import kotlinx.android.synthetic.main.layout_panel_numbers.*
import org.jetbrains.anko.textColor
import javax.inject.Inject

class AmountActivity : AppCompatActivity(), AmountContract.View, View.OnClickListener {
    @Inject
    lateinit var presenter: AmountContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amount)
        AndroidInjection.inject(this)
        initExtras()
        initListeners()
        initView()
    }

    private fun initExtras() {
        intent?.let {
            presenter.updateContacts(intent.getParcelableArrayListExtra(INTENT_KEY_CONTACTS))
        }
    }

    private fun initListeners() {
        tvPanelOne.setOnClickListener(this)
        tvPanelTwo.setOnClickListener(this)
        tvPanelThree.setOnClickListener(this)
        tvPanelFour.setOnClickListener(this)
        tvPanelFive.setOnClickListener(this)
        tvPanelSix.setOnClickListener(this)
        tvPanelSeven.setOnClickListener(this)
        tvPanelEight.setOnClickListener(this)
        tvPanelNine.setOnClickListener(this)
        tvPanelDot.setOnClickListener(this)
        tvPanelZero.setOnClickListener(this)
        tvPanelRemove.setOnClickListener(this)
        btnSplit.setOnClickListener(this)
    }

    private fun initView() {
        disableSplit()
        btnSplit.show()
    }

    override fun updateAmount(amount: String) {
        tvAmount.text = amount
    }

    override fun updateDecimals(decimals: String, visible: Boolean) {
        tvDecimals.text = decimals
        if(visible) tvDecimals.show() else tvDecimals.hide()
    }

    override fun enableSplit(split: String) {
        with(btnSplit) {
            background = getDrawable(R.drawable.background_button_blue)
            textColor = getColor(R.color.white)
            text = getString(R.string.button_split_amount, split)
            isEnabled = true
        }
    }

    override fun disableSplit() {
        with(btnSplit) {
            background = getDrawable(R.drawable.background_button_grey)
            textColor = getColor(R.color.black)
            text = getString(R.string.button_split_amount, AmountPresenter.SPLIT_EMPTY)
            isEnabled = false
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvPanelDot -> presenter.onDotSelected()
            R.id.tvPanelRemove -> presenter.onBackspaceSelected()
            R.id.btnSplit -> presenter.onSplitClicked()
            else -> presenter.onNumberSelected((v as TextView).text.toString())
        }
    }

    companion object {
        const val INTENT_KEY_CONTACTS = "key:contacts"

        fun getIntent(context: Context, contacts: ArrayList<Contact>): Intent = Intent(context, AmountActivity::class.java).run {
            putParcelableArrayListExtra(INTENT_KEY_CONTACTS, contacts)
        }
    }
}