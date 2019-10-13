package com.example.githubtest.ui.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtest.R
import com.example.githubtest.model.models.GitHubRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repository_details.*


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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadDataFromIntent()
        setupViews()
    }

    private fun loadDataFromIntent() {
        gitHubRepository = Gson().fromJson(
            intent.getStringExtra(ARGS_REPOSITORY),
            GitHubRepository::class.java
        )
    }

    private fun setupViews() {
        title = gitHubRepository.fullName
        ownerNameTextView.text = gitHubRepository.owner!!.login

        Picasso
            .get()
            .load(gitHubRepository.owner!!.avatarUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(avatarImageView);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_url -> {
                openRepositoryUrl()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openRepositoryUrl() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gitHubRepository.htmlUrl))
        startActivity(browserIntent)
    }
}
