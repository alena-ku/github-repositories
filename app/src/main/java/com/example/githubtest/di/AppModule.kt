package com.example.githubtest.di

import com.example.githubtest.app.GitHubApi
import com.example.githubtest.model.GitHubRepositoriesRepository
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideGitHubRepositoriesRepository(gitHubApi: GitHubApi): GitHubRepositoriesRepository
            = GitHubRepositoriesRepository(gitHubApi)

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi
            = retrofit.create(GitHubApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit
            = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

}

