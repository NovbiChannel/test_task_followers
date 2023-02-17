package com.example.test_task_followers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_task_followers.R
import com.example.test_task_followers.databinding.FragmentUserSearchBinding

class UserSearchFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserSearchBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUserSearchBinding.bind(view)

        binding.myProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_userSearchFragment_to_userDetailFragment)
        }

        binding.clearButton.setOnClickListener {
            binding.edUserSearch.text.clear()
        }
    }
}