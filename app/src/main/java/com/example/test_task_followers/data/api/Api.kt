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