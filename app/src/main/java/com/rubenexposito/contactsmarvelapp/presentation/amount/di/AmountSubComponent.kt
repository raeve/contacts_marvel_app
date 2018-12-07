package com.rubenexposito.contactsmarvelapp.presentation.amount.di

import com.rubenexposito.contactsmarvelapp.di.ActivityModule
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [ActivityModule::class, AmountModule::class])
interface AmountSubComponent : AndroidInjector<AmountActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AmountActivity>()
}