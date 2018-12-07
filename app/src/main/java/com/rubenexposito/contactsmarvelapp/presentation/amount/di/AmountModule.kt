package com.rubenexposito.contactsmarvelapp.presentation.amount.di

import android.app.Activity
import com.rubenexposito.contactsmarvelapp.Navigator
import com.rubenexposito.contactsmarvelapp.di.PerActivity
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountActivity
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountContract
import com.rubenexposito.contactsmarvelapp.presentation.amount.AmountPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

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