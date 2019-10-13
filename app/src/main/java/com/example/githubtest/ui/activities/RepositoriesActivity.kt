package com.example.githubtest.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubtest.R
import com.example.githubtest.model.models.GitHubRepository
import com.example.githubtest.ui.RepositoriesAdapter
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivity : AppCompatActivity(), RepositoriesAdapter.Interaction {


    private lateinit var gitHubRepositories: List<GitHubRepository>

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
            Array<GitHubRepository>::class.java).asList()

        repositoriesRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        val repositoriesAdapter = RepositoriesAdapter(this)
        repositoriesRecyclerView.adapter = repositoriesAdapter
        repositoriesAdapter.submitList(gitHubRepositories)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun itemClicked(item: GitHubRepository) {
        startActivity(RepositoryDetailsActivity.createIntent(this, item))
    }

}
