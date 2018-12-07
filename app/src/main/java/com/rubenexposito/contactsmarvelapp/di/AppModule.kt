package com.rubenexposito.contactsmarvelapp.di

import android.content.Context
import com.rubenexposito.contactsmarvelapp.BaseApplication
import com.rubenexposito.contactsmarvelapp.presentation.amount.di.AmountSubComponent
import com.rubenexposito.contactsmarvelapp.presentation.contacts.di.ContactsSubComponent
import com.rubenexposito.contactsmarvelapp.presentation.split.di.SplitSubComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module(
    subcomponents = [ContactsSubComponent::class, AmountSubComponent::class, SplitSubComponent::class]
)
class AppModule {

    @Provides
    fun context(application: BaseApplication): Context = application.applicationContext

    @Provides
    @Named("observeOn")
    fun provideObserveOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("subscribeOn")
    fun provideSubscribeOnScheduler(): Scheduler = Schedulers.io()

}

