package com.rubenexposito.contactsmarvelapp.presentation.contacts.di

import com.rubenexposito.contactsmarvelapp.di.ActivityModule
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [ActivityModule::class, ContactsModule::class])
interface ContactsSubComponent : AndroidInjector<ContactsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ContactsActivity>()
}