package com.example.githubtest.di

import com.example.githubtest.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewsModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

}