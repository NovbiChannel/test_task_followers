package com.example.test_task_followers.data.api

import com.example.test_task_followers.data.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String,
    ): Call<DetailUserResponce>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getReposUser(
        @Path("username") username: String
    ): Call<ArrayList<ReposUserResponce>>

    @GET("users")
    @Headers("Authorization: token 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")
    fun getAllUser(
        @Query("since") since: Int = 0,
    ): Call<ArrayList<User>>

    @GET("user")
    suspend fun getCurrentUser(
    ): RemoteGithubUser
}