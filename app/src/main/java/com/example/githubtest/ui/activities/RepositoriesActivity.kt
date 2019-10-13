package com.example.githubtest.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtest.R
import com.example.githubtest.model.models.GitHubRepository
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivity : AppCompatActivity() {

    private lateinit var gitHubRepositories: Array<GitHubRepository>

    companion object{
        private const val ARGS_REPOSITORIES = "repositories"

        fun createIntent(context: Context, gitHubRepositories: List<GitHubRepository>) =
            Intent(context, RepositoriesActivity::class.java).apply {
                putExtra(ARGS_REPOSITORIES, Gson().toJson(gitHubRepositories))
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        setSupportActionBar(toolbar)

        gitHubRepositories = Gson().fromJson(intent.getStringExtra(ARGS_REPOSITORIES),
            Array<GitHubRepository>::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
