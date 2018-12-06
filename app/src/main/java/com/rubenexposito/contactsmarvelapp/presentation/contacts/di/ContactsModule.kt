package com.rubenexposito.contactsmarvelapp.presentation.contacts.di

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.data.ContactsLoaderListener
import com.rubenexposito.contactsmarvelapp.data.ContactsRepository
import com.rubenexposito.contactsmarvelapp.data.ContactsRepositoryImpl
import com.rubenexposito.contactsmarvelapp.data.MarvelRepository
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.domain.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.domain.model.ContactMapper
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsActivity
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsContract
import com.rubenexposito.contactsmarvelapp.presentation.contacts.ContactsPresenter
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.ContactListener
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.ContactsAdapter
import com.rubenexposito.contactsmarvelapp.presentation.contacts.adapter.SelectedContactsAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
abstract class ContactsModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(activity: ContactsActivity): ContactsContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: ContactsActivity): Activity


    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        fun provideContactsRepository(
            activity: ContactsActivity,
            listener: ContactsLoaderListener
        ): ContactsRepository =
            ContactsRepositoryImpl(activity, ContactMapper(), listener)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideGetContactsUseCase(
            marvelRepository: MarvelRepository,
            @Named("observeOn") observeOn: Scheduler,
            @Named("subscribeOn") subscribeOn: Scheduler
        ): GetContactsUseCase = GetContactsUseCase(marvelRepository, observeOn, subscribeOn)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideContactsPresenter(
            view: ContactsContract.View,
            useCase: GetContactsUseCase,
            navigator: Navigator
        ): ContactsContract.Presenter = ContactsPresenter(view, useCase, navigator)

        @Provides
        @PerActivity
        @JvmStatic
        fun provideContactsLoaderListener(presenter: ContactsContract.Presenter): ContactsLoaderListener = presenter

        @Provides
        @PerActivity
        @JvmStatic
        fun provideContactListener(presenter: ContactsContract.Presenter): ContactListener = presenter

        @Provides
        @PerActivity
        @JvmStatic
        fun provideContactsAdapter(listener: ContactListener) = ContactsAdapter(listener)

        @Provides
        @PerActivity
        @JvmStatic
        fun provideSelectedContactsAdapter(listener: ContactListener) = SelectedContactsAdapter(listener)
    }
}