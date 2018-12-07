package com.rubenexposito.contactsmarvelapp.presentation.split.di

import com.rubenexposito.contactsmarvelapp.di.ActivityModule
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.presentation.split.SplitActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [ActivityModule::class, SplitModule::class])
interface SplitSubComponent : AndroidInjector<SplitActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplitActivity>()
}