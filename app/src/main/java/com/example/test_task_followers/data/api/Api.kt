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