package com.example.githubtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtest.R
import com.example.githubtest.model.models.GitHubRepository
import kotlinx.android.synthetic.main.list_item_repository.view.*

class RepositoriesAdapter(private val interaction: Interaction? = null) :
    ListAdapter<GitHubRepository, RepositoriesAdapter.ViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_repository, parent, false
        )
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val clicked = getItem(adapterPosition)
            interaction?.itemClicked(clicked)
        }

        fun bind(item: GitHubRepository) {
            with(itemView) {
                setTextAndVisibility(repositoryNameTextView, R.string.repository_name, item.name)
                setTextAndVisibility(repositoryDescriptionTextView, R.string.repository_description, item.description)
                setTextAndVisibility(repositoryCreationDateTextView, R.string.repository_creation_date, item.createdAt)
                setTextAndVisibility(repositoryUpdateDateTextView, R.string.repository_update_date, item.updatedAt)
                setTextAndVisibility(repositoryStarsTextView, R.string.repository_stars, item.stargazersCount)
                setTextAndVisibility(repositoryLanguageTextView, R.string.repository_language, item.language)
            }
        }

        private fun setTextAndVisibility(textView: TextView, resourceId: Int, text: Any?) {
            textView.visibility = if (text == null) View.GONE else View.VISIBLE

            text?.let {
                textView.text = itemView.resources.getString(resourceId, it)
            }
        }
    }

    interface Interaction {
        fun itemClicked(item: GitHubRepository)
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<GitHubRepository>() {
        override fun areItemsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository) = oldItem == newItem

        override fun areContentsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository) = oldItem.id == newItem.id
    }
}