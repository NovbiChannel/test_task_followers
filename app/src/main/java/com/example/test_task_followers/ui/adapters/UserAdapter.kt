package com.example.test_task_followers.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_task_followers.data.models.User
import com.example.test_task_followers.databinding.ListItemUserBinding

class UserAdapter(private val clickListener: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback) {

    private var listData = ArrayList<User>()
    private lateinit var context: Context


    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: ArrayList<User>){
        listData.clear()
        listData.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(private var binding: ListItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data: User){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.avatar_url)
                    .into(ivUser)
                tvUserName.text = data.login
                tvFollowersCount.text = "Подписичков: ${data.followers}"

            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<User>(){

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return (oldItem.id == newItem.id
                    && oldItem.login == newItem.login && oldItem.avatar_url == newItem.avatar_url &&
                    oldItem.followers == newItem.followers)
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        context = parent.context
        return UserViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listData[position])
        holder.itemView.setOnClickListener {
            clickListener(listData[position])
        }
    }

    override fun getItemCount() = listData.size
}