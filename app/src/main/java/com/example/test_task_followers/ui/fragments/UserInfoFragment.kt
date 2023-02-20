package com.example.test_task_followers.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.test_task_followers.R
import com.example.test_task_followers.databinding.FragmentUserInfoBinding
import com.example.test_task_followers.ui.viewmodels.UserInfoViewModel
import com.example.test_task_followers.utils.launchAndCollectIn

class UserInfoFragment: Fragment(R.layout.fragment_user_info) {

    private val viewModel: UserInfoViewModel by viewModels()

    private val binding by viewBinding(FragmentUserInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.userInfoFlow.launchAndCollectIn(viewLifecycleOwner) { userInfo ->
            Glide.with(this@UserInfoFragment)
                .load(userInfo?.avatar_url)
                .into(binding.ivUser)
            binding.tvUserName.text = userInfo?.name
            binding.tvUserDescription.text = userInfo?.bio
            binding.tvFollowers.text = userInfo?.followers.toString()
            binding.tvFollowing.text = userInfo?.following.toString()
            binding.tvRepo.text = userInfo?.public_repos.toString()
        }

    }
}