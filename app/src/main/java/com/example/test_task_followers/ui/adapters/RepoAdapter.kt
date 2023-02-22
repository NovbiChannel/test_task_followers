package com.example.test_task_followers.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_followers.data.models.ReposUserResponce
import com.example.test_task_followers.databinding.ListItemRepoBinding
import com.example.test_task_followers.other.Constants.DateFormat.FORMAT_DATE_DD_MM_YYYY
import com.example.test_task_followers.other.Constants.DateFormat.FORMAT_DATE_TIMEZONE
import com.example.test_task_followers.ui.fragments.UserDetailFragment
import java.text.SimpleDateFormat

class RepoAdapter : ListAdapter<ReposUserResponce, RepoAdapter.RepoViewHolder>(DiffCallback) {

        private var listData = ArrayList<ReposUserResponce>()
        private lateinit var context: Context


        @SuppressLint("NotifyDataSetChanged")
        fun setData(repo: ArrayList<ReposUserResponce>){
            listData.clear()
            listData.addAll(repo)
            notifyDataSetChanged()
        }

        class RepoViewHolder(private var binding: ListItemRepoBinding
        ) : RecyclerView.ViewHolder(binding.root){
            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            fun bind(data: ReposUserResponce){
                with(binding){
                    tvRepoName.text = data.name
                    tvRepoLink.text = data.html_url
                    tvRepoDescription.text = data.description
                    if (tvRepoDescription.text.isEmpty()) {
                        tvRepoDescription.visibility = View.GONE
                    }
                    tvRepoLanguage.text = data.language
                    tvRepoVisibility.text = data.visibility
                    tvForks.text = data.forks.toString()
                    tvStars.text = data.stargazers_count.toString()

                    val givenDate = data.pushed_at
                    val formatter = SimpleDateFormat(FORMAT_DATE_TIMEZONE)
                    val date = formatter.parse(givenDate)
                    val formatOutput = SimpleDateFormat(FORMAT_DATE_DD_MM_YYYY)
                    val formattedDate: String = formatOutput.format(date!!)

                    tvPushedAt.text = "Последний коммит: $formattedDate"
                }
            }
        }

        companion object DiffCallback: DiffUtil.ItemCallback<ReposUserResponce>(){

            override fun areItemsTheSame(oldItem: ReposUserResponce, newItem: ReposUserResponce): Boolean {
                return (oldItem.id == newItem.id && oldItem.description == newItem.description &&
                        oldItem.forks == newItem.forks && oldItem.html_url == newItem.html_url &&
                        oldItem.language == newItem.language && oldItem.name == newItem.name &&
                        oldItem.pushed_at == newItem.pushed_at && oldItem.stargazers_count == newItem.stargazers_count &&
                        oldItem.visibility == newItem.visibility)
            }

            override fun areContentsTheSame(oldItem: ReposUserResponce, newItem: ReposUserResponce): Boolean {
                return oldItem == newItem
            }

        }


        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RepoViewHolder {
            context = parent.context
            return RepoViewHolder(
                ListItemRepoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }


        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
            holder.bind(listData[position])
        }

        override fun getItemCount() = listData.size
}