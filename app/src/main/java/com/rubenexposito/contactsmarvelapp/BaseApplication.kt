package com.rubenexposito.contactsmarvelapp

import android.app.Activity
import android.app.Application
import com.rubenexposito.contactsmarvelapp.di.AppComponent
import com.rubenexposito.contactsmarvelapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var component: AppComponent

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        component.inject(this)
    }
}