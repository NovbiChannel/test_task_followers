package com.example.test_task_followers.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.test_task_followers.R
import com.example.test_task_followers.data.auth.TokenStorage
import com.example.test_task_followers.ui.adapters.UserAdapter
import com.example.test_task_followers.databinding.FragmentUserSearchBinding
import com.example.test_task_followers.other.Constants.AVATAR_URL
import com.example.test_task_followers.other.Constants.BIO_TAG
import com.example.test_task_followers.other.Constants.FOLLOWERS_TAG
import com.example.test_task_followers.other.Constants.FOLLOWING_TAG
import com.example.test_task_followers.other.Constants.LOGIN_TAG
import com.example.test_task_followers.other.Constants.NAME_TAG
import com.example.test_task_followers.other.Constants.REPO_TAG
import com.example.test_task_followers.other.Constants.TOKEN
import com.example.test_task_followers.ui.viewmodels.DetailUserViewModel
import com.example.test_task_followers.ui.viewmodels.MainViewModel
import com.example.test_task_followers.ui.viewmodels.UserInfoViewModel
import com.example.test_task_followers.utils.launchAndCollectIn
import kotlinx.coroutines.*

@Suppress("LABEL_NAME_CLASH")
class UserSearchFragment: Fragment() {

    private var _binding: FragmentUserSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private val vieModelU by viewModels<UserInfoViewModel>()
    private val vieModelD by viewModels<DetailUserViewModel>()
    private val token = TokenStorage.accessToken

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        val adapter = UserAdapter {
            val action = UserSearchFragmentDirections.actionUserSearchFragmentToUserDetailFragment(it)
            this.findNavController().navigate(action)
        }
        adapter.notifyDataSetChanged()
        showLoading(true)

        vieModelU.userInfoFlow.launchAndCollectIn(viewLifecycleOwner) { userInfo ->
            if (userInfo?.login != null) {
                editor.apply {
                    putString(LOGIN_TAG, userInfo.login)
                    putString(NAME_TAG, userInfo.name)
                    putString(BIO_TAG, userInfo.bio)
                    putString(FOLLOWERS_TAG, userInfo.followers.toString())
                    putString(FOLLOWING_TAG, userInfo.following.toString())
                    putString(REPO_TAG, userInfo.public_repos.toString())
                    putString(AVATAR_URL, userInfo.avatar_url)
                    apply()
                }
            }
        }
        val userLogin = sharedPreference.getString(LOGIN_TAG, null)

        if (userLogin == null) {
            binding.myProfileButton.setOnClickListener {
                findNavController().navigate(R.id.action_userSearchFragment_to_authFragment)
            }
        } else {
            Glide.with(this@UserSearchFragment)
                .load(sharedPreference.getString(AVATAR_URL, null))
                .into(binding.myProfileButton)

            binding.myProfileButton.setOnClickListener {
                findNavController().navigate(R.id.action_userSearchFragment_to_userInfoFragment)
            }
        }

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(context)
            rvUser.adapter = adapter

            edUserSearch.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUsers().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                for(user in it) {
                    vieModelD.setUserDetail(user.login)
                    vieModelD.getUserDetail().observe(viewLifecycleOwner) { userResponce ->
                        if (user.id == userResponce.id) {
                            user.followers = userResponce.followers
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2500)
                                adapter.setData(it)
                                showLoading(false)
                                binding.rvUser.visibility = View.VISIBLE
                                binding.llNoUser.visibility = View.GONE
                            }
                        }
                    }
                }
            } else {
                binding.llNoUser.visibility = View.VISIBLE
                binding.rvUser.visibility = View.GONE
                showLoading(false)
            }
        }

        binding.clearButton.setOnClickListener {
            binding.edUserSearch.text.clear()
        }
    }

    private fun searchUser(){
        binding.apply {
            val query = edUserSearch.text.toString()
            if (query.isEmpty()){
                return@apply
            } else {
                showLoading(true)
                viewModel.setSearchUsers(query)
            }
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
