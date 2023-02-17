package com.example.test_task_followers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_task_followers.R
import com.example.test_task_followers.databinding.FragmentUserDetailsBinding

class UserDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserDetailsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUserDetailsBinding.bind(view)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack(R.id.userDetailFragment, false)
            findNavController().navigate(R.id.action_userDetailFragment_to_userSearchFragment)
        }
    }
}