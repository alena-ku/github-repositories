package com.example.githubtest.ui.activities

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtest.R
import com.example.githubtest.model.GitHubRepositoriesRepository
import com.example.githubtest.model.models.GitHubRepository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var gitHubRepositoriesRepository: GitHubRepositoriesRepository
    private var requestSubscription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showRepositoriesButton.setOnClickListener {
            toggleLoading(true)

            requestSubscription = gitHubRepositoriesRepository.getByUserName(userTextInputEditText.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    toggleLoading(false)
                    startRepositoriesActivity(it)
                }, { error ->
                    loadingFailed(error.message!!)
                    toggleLoading(false)
                })
        }
    }

    override fun onDestroy() {
        requestSubscription?.dispose()
        super.onDestroy()
    }

    private fun startRepositoriesActivity(gitHubRepositories: List<GitHubRepository>) {
        startActivity(
            RepositoriesActivity.createIntent(
                this, userTextInputEditText.text.toString(), gitHubRepositories
            )
        )
    }

    fun loadingFailed(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }

    fun toggleLoading(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
}
