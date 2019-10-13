package com.example.githubtest.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtest.R
import com.example.githubtest.model.models.GitHubRepository
import com.example.githubtest.ui.RepositoriesAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_repositories.*


class RepositoriesActivity : AppCompatActivity(), RepositoriesAdapter.Interaction {

    private lateinit var gitHubRepositories: List<GitHubRepository>
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    companion object {
        private const val ARGS_USERNAME = "username"
        private const val ARGS_REPOSITORIES = "repositories"
        private const val ARGS_SORT_BY_CREATED = "sortByCreated"

        fun createIntent(
            context: Context,
            username: String,
            gitHubRepositories: List<GitHubRepository>
        ) =
            Intent(context, RepositoriesActivity::class.java).apply {
                putExtra(ARGS_USERNAME, Gson().toJson(username))
                putExtra(ARGS_REPOSITORIES, Gson().toJson(gitHubRepositories))
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDataFromIntent()
    }

    private fun loadDataFromIntent() {
        var username = Gson().fromJson(
            intent.getStringExtra(ARGS_USERNAME),
            String::class.java
        )

        gitHubRepositories = Gson().fromJson(
            intent.getStringExtra(ARGS_REPOSITORIES),
            Array<GitHubRepository>::class.java
        ).asList()

        repositoriesAdapter = RepositoriesAdapter(username, this)
        repositoriesRecyclerView.adapter = repositoriesAdapter

        setSortByCreated(intent.getBooleanExtra(ARGS_SORT_BY_CREATED, true))
    }

    override fun itemClicked(item: GitHubRepository) {
        startActivity(RepositoryDetailsActivity.createIntent(this, item))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_repos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_created -> {
                setSortByCreated(true)
                true
            }
            R.id.action_sort_name -> {
                setSortByCreated(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setSortByCreated(sortByCreated: Boolean) {
        intent.putExtra(ARGS_SORT_BY_CREATED, sortByCreated)

        val list =
            if (sortByCreated)
                gitHubRepositories.sortedBy { it.createdAt }
            else
                gitHubRepositories.sortedBy { it.name?.toLowerCase() }

        repositoriesAdapter.submitList(list)
    }
}
