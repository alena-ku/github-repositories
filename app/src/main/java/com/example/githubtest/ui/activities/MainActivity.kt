package com.example.githubtest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubtest.R
import com.example.githubtest.model.GitHubRepositoriesRepository
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var gitHubRepositoriesRepository: GitHubRepositoriesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
