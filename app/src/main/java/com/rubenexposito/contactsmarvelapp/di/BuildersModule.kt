package com.rubenexposito.contactsmarvelapp.di

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsActivity
import com.rubenexposito.contactsmarvelapp.presentation.contacts.di.ContactsSubComponent
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
}