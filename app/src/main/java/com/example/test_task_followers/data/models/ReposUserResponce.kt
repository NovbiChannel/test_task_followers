package com.example.test_task_followers.data.models

data class ReposUserResponce (
    val description: String,
    val forks: Int,
    val html_url: String,
    val id: Int,
    val language: String,
    val name: String,
    val pushed_at: String,
    val stargazers_count: Int,
    val visibility: String,
)