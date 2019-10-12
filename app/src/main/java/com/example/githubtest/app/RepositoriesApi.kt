package com.example.githubtest.app

import com.example.githubtest.model.models.GitHubRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoriesApi {
    @GET("users/{userName}/repos")
    fun requestUserRepositories(@Path("userName") userName: String): Single<List<GitHubRepository>>
}