package com.example.test_task_followers.data.models

data class DetailUserResponce (
    val id: Int,
    val avatar_url: String,
    val repos_url: String,
    val name: String,
    val bio: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val login: String
        )