package com.rubenexposito.contactsmarvelapp.presentation

import androidx.annotation.StringRes

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(@StringRes stringRes: Int)
}