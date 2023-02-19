package com.example.test_task_followers.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.test_task_followers.R
import com.example.test_task_followers.adapters.RepoAdapter
import com.example.test_task_followers.databinding.FragmentUserDetailsBinding
import com.example.test_task_followers.ui.viewmodels.DetailUserViewModel

class UserDetailFragment: Fragment() {

    private val viewModel by viewModels<DetailUserViewModel>()
    private val bundleArgs: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserDetailsBinding.inflate(inflater, container, false).root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUserDetailsBinding.bind(view)
        val userArgs = bundleArgs.User
        val adapter = RepoAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvRepo.layoutManager = LinearLayoutManager(context)
            rvRepo.adapter = adapter
        }

        viewModel.setUserDetail(userArgs.login!!)
        viewModel.getUserDetail().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.apply {
                    tvUserName.text = it.name
                    tvUserDescription.text = it.bio
                    tvFollowers.text = it.followers.toString()
                    tvFollowing.text = it.following.toString()
                    tvRepo.text = it.public_repos.toString()
                    Glide.with(this@UserDetailFragment)
                        .load(it.avatar_url)
                        .into(ivUser)
                }
            }
        }
        viewModel.setReposUser(userArgs.login)
        viewModel.getReposUser().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setData(it)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_userDetailFragment_to_userSearchFragment)
        }
    }
}