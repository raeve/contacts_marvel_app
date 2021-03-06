package com.rubenexposito.contactsmarvelapp.presentation.common

import androidx.annotation.StringRes

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(@StringRes stringRes: Int)
}