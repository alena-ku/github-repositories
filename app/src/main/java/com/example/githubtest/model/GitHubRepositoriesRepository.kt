package com.example.githubtest.model

import com.example.githubtest.app.GitHubApi
import com.example.githubtest.model.models.GitHubRepository
import io.reactivex.Single

class GitHubRepositoriesRepository(private val gitHubApi: GitHubApi) {
    fun getByUserName(userName: String): Single<List<GitHubRepository>>
        = gitHubApi.requestUserRepositories(userName)

}