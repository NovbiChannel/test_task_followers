package com.example.test_task_followers.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_task_followers.R
import com.example.test_task_followers.adapters.UserAdapter
import com.example.test_task_followers.databinding.FragmentUserSearchBinding
import com.example.test_task_followers.ui.viewmodels.MainViewModel

@Suppress("LABEL_NAME_CLASH")
class UserSearchFragment: Fragment() {

    private var _binding: FragmentUserSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

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

        val adapter = UserAdapter {
            val action = UserSearchFragmentDirections.actionUserSearchFragmentToUserDetailFragment(it)
            this.findNavController().navigate(action)
        }
        adapter.notifyDataSetChanged()

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
            if (it != null) {
                adapter.setData(it)
            }
        }


        binding.myProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_userSearchFragment_to_authFragment)
        }

        binding.clearButton.setOnClickListener {
            binding.edUserSearch.text.clear()
        }
    }

    private fun searchUser(){
        binding.apply {
            val query = edUserSearch.text.toString()
            if (query.isEmpty()) return@apply
            viewModel.setSearchUsers(query)
        }
    }
}
