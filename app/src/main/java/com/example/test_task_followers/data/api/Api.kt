package com.example.test_task_followers.data.api

import com.example.test_task_followers.data.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
interface Api {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String,
        @Header("Authorization") accessToken: String
    ): Call<UserResponse>
    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String,
        @Header("Authorization") accessToken: String
    ): Call<DetailUserResponce>
    @GET("users/{username}/repos")
    fun getReposUser(
        @Path("username") username: String,
        @Header("Authorization") accessToken: String
    ): Call<ArrayList<ReposUserResponce>>
    @GET("users")
    fun getAllUser(
        @Query("since") since: Int = 0,
        @Header("Authorization") accessToken: String
    ): Call<ArrayList<User>>
    @GET("user")
    suspend fun getCurrentUser(
    ): RemoteGithubUser
}