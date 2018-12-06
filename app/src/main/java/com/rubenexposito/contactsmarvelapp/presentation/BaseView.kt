package com.rubenexposito.contactsmarvelapp.presentation

import android.support.annotation.StringRes

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(@StringRes stringRes: Int)
}