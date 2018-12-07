package com.rubenexposito.contactsmarvelapp.presentation.split.di

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.presentation.common.ContactsAdapter
import com.rubenexposito.contactsmarvelapp.presentation.split.SplitActivity
import com.rubenexposito.contactsmarvelapp.presentation.split.SplitContract
import com.rubenexposito.contactsmarvelapp.presentation.split.SplitPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SplitModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(activity: SplitActivity): SplitContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: SplitActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        fun provideContactsAdapter() = ContactsAdapter()

        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideAmountPresenter(
            view: SplitContract.View
        ): SplitContract.Presenter = SplitPresenter(view)
    }
}