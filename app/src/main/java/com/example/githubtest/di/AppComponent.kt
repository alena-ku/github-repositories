package com.example.githubtest.di

import android.content.Context
import com.example.githubtest.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ViewsModule::class
])
interface AppComponent : AndroidInjector<App>  {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun applicationContext(context: Context): Builder
    }

}