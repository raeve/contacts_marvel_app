package com.rubenexposito.contactsmarvelapp.di

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountActivity
import com.rubenexposito.contactsmarvelapp.presentation.amount.di.AmountSubComponent
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsActivity
import com.rubenexposito.contactsmarvelapp.presentation.contacts.di.ContactsSubComponent
import com.rubenexposito.contactsmarvelapp.presentation.split.SplitActivity
import com.rubenexposito.contactsmarvelapp.presentation.split.di.SplitSubComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(ContactsActivity::class)
    abstract fun bindContactsActivityInjectorFactory(builder: ContactsSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(AmountActivity::class)
    abstract fun bindAmountActivityInjectorFactory(builder: AmountSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(SplitActivity::class)
    abstract fun bindSplitActivityInjectorFactory(builder: SplitSubComponent.Builder): AndroidInjector.Factory<out Activity>
}