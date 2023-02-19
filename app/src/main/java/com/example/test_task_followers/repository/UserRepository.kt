package com.example.test_task_followers.repository

import com.example.test_task_followers.data.models.RemoteGithubUser
import com.example.test_task_followers.network.Networking

class UserRepository {
    suspend fun getUserInformation(): RemoteGithubUser {
        return Networking.githubApi.getCurrentUser()
    }
}