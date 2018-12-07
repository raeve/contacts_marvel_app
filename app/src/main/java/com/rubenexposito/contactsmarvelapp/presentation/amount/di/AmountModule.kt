package com.rubenexposito.contactsmarvelapp.presentation.amount.di

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.data.ContactsRepository
import com.rubenexposito.contactsmarvelapp.data.ContactsRepositoryImpl
import com.rubenexposito.contactsmarvelapp.data.MarvelRepository
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.domain.GetContactsUseCase
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountActivity
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountContract
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountPresenter
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
abstract class AmountModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(activity: AmountActivity): AmountContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: AmountActivity): Activity


    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideAmountPresenter(
            view: AmountContract.View,
            navigator: Navigator
        ): AmountContract.Presenter = AmountPresenter(view, navigator)
    }
}