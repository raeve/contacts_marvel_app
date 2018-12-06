package com.rubenexposito.contactsmarvelapp.di

import com.rubenexposito.contactsmarvelapp.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (NetworkModule::class),
        (BuildersModule::class)
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication) : Builder
        fun build() : AppComponent
    }

    fun inject(application: BaseApplication)
}