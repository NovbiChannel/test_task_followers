package com.example.test_task_followers.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
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
import com.example.test_task_followers.ui.adapters.RepoAdapter
import com.example.test_task_followers.databinding.FragmentUserDetailsBinding
import com.example.test_task_followers.databinding.FragmentUserSearchBinding
import com.example.test_task_followers.ui.viewmodels.DetailUserViewModel

class UserDetailFragment: Fragment() {

    private val viewModel by viewModels<DetailUserViewModel>()
    private val bundleArgs: UserDetailFragmentArgs by navArgs()

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    if (tvUserName.text.isEmpty()) {
                        tvUserName.text = it.login
                    }
                    tvUserDescription.text = it.bio
                    if (tvUserDescription.text.isEmpty()) {
                        tvUserDescription.visibility = View.GONE
                    }
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
        showLoading(true)
        viewModel.getReposUser().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvRepo.visibility = View.GONE
                binding.llNoRepo.visibility = View.VISIBLE
            } else {
                adapter.setData(it)
                showLoading(false)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_userDetailFragment_to_userSearchFragment)
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}