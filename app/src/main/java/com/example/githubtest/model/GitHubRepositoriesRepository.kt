package com.example.githubtest.model

import com.example.githubtest.app.RepositoriesApi
import com.example.githubtest.model.models.GitHubRepository
import io.reactivex.Single

class GitHubRepositoriesRepository(private val repositoriesApi: RepositoriesApi) {
    fun getByUserName(userName: String): Single<List<GitHubRepository>>
        = repositoriesApi.requestUserRepositories(userName)

}