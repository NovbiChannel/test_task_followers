package com.example.test_task_followers.data.auth.models

data class TokensModel(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String
)