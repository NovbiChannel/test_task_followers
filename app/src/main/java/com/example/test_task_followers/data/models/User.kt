package com.example.test_task_followers.data.models

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    var followers: Int,
): java.io.Serializable