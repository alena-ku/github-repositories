package com.example.githubtest.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtest.R
import com.example.githubtest.model.models.GitHubRepository
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_repository_details.toolbar
import kotlinx.android.synthetic.main.list_item_repository.*

class RepositoryDetailsActivity : AppCompatActivity() {

    private lateinit var gitHubRepository: GitHubRepository

    companion object{
        private const val ARGS_REPOSITORY = "repository"

        fun createIntent(context: Context, gitHubRepository: GitHubRepository) =
            Intent(context, RepositoryDetailsActivity::class.java).apply {
                putExtra(ARGS_REPOSITORY, Gson().toJson(gitHubRepository))
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)
        setSupportActionBar(toolbar)

        gitHubRepository = Gson().fromJson(intent.getStringExtra(ARGS_REPOSITORY),
            GitHubRepository::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repositoryNameTextView.text = gitHubRepository.fullName
    }
}
