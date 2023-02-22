package com.example.test_task_followers.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteGithubUser(
    val id: Long,
    val login: String,
    val name: String,
    val avatar_url: String,
    val repos_url: String,
    val bio: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
)