package com.example.test_task_followers.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.test_task_followers.R
import com.example.test_task_followers.ui.adapters.RepoAdapter
import com.example.test_task_followers.databinding.FragmentUserInfoBinding
import com.example.test_task_followers.other.Constants
import com.example.test_task_followers.other.Constants.AVATAR_URL
import com.example.test_task_followers.other.Constants.BIO_TAG
import com.example.test_task_followers.other.Constants.CANCEL_LOGOUT_USER_DIALOG_TAG
import com.example.test_task_followers.other.Constants.FOLLOWERS_TAG
import com.example.test_task_followers.other.Constants.FOLLOWING_TAG
import com.example.test_task_followers.other.Constants.LOGIN_TAG
import com.example.test_task_followers.other.Constants.NAME_TAG
import com.example.test_task_followers.other.Constants.REPO_TAG
import com.example.test_task_followers.ui.viewmodels.DetailUserViewModel
import com.example.test_task_followers.ui.viewmodels.UserInfoViewModel
import com.example.test_task_followers.utils.launchAndCollectIn

class UserInfoFragment: Fragment(R.layout.fragment_user_info) {

    private val viewModel: UserInfoViewModel by viewModels()
    private val viewModelD: DetailUserViewModel by viewModels()

    private val binding by viewBinding(FragmentUserInfoBinding::bind)

    private val logoutResponce = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.webLogoutComplete()
        } else {
            // логаут отменен
            // делаем complete тк github не редиректит после логаута и пользователь закрывает CCT
            viewModel.webLogoutComplete()
        }
    }


    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        val adapter = RepoAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvRepo.layoutManager = LinearLayoutManager(context)
            rvRepo.adapter = adapter

            Glide.with(this@UserInfoFragment)
                .load(sharedPreference.getString(Constants.AVATAR_URL, null))
                .into(ivUser)
            tvUserName.text = sharedPreference.getString(NAME_TAG, null)
            tvUserDescription.text = sharedPreference.getString(BIO_TAG, null)
            tvFollowers.text = sharedPreference.getString(FOLLOWERS_TAG, null)
            tvFollowing.text = sharedPreference.getString(FOLLOWING_TAG, null)
            tvRepo.text = sharedPreference.getString(REPO_TAG, null)
        }

        sharedPreference.getString(LOGIN_TAG, null)?.let { viewModelD.setReposUser(it) }
        showLoading(true)
        viewModelD.getReposUser().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvRepo.visibility = View.GONE
                binding.llNoRepo.visibility = View.VISIBLE
            } else {
                adapter.setData(it)
                showLoading(false)
            }
        }

        binding.setting.setOnClickListener {
            showLogoutUserDialog()
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_userInfoFragment_to_userSearchFragment)
        }

        viewModel.logoutPageFlow.launchAndCollectIn(viewLifecycleOwner) {
            logoutResponce.launch(it)
        }

        viewModel.logoutCompletedFlow.launchAndCollectIn(viewLifecycleOwner) {
            editor.apply {
                putString(LOGIN_TAG, null)
                putString(NAME_TAG, null)
                putString(BIO_TAG, null)
                putString(FOLLOWERS_TAG, null)
                putString(FOLLOWING_TAG, null)
                putString(REPO_TAG, null)
                putString(AVATAR_URL, null)
                apply()
            }
            findNavController().navigate(R.id.action_userInfoFragment_to_userSearchFragment)
        }


    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    private fun showLogoutUserDialog() {
        LogoutUserDialog().apply {
            setYesListener {
                viewModel.logout()
            }
        }.show(parentFragmentManager, CANCEL_LOGOUT_USER_DIALOG_TAG)
    }
}