package com.example.test_task_followers.api

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
        @Path("username") username: String
    ): Call<DetailUserResponce>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getReposUser(
        @Path("username") username: String
    ): Call<ArrayList<ReposUserResponce>>

//    @GET("users/{username}/followers")
//    @Headers("Authorization: token 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")
//    fun getFollowersCount(
//        @Path("username") username: String
//    ) : Call <ArrayList<TestItem>>

    @GET("users")
    @Headers("Authorization: token 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")
    fun getAllUser(
        @Query("since") since: Int = 0,
        @Query("per_page") perPage: Int = 50
    ): Call<ArrayList<User>>

    @GET("user")
    suspend fun getCurrentUser(
    ): RemoteGithubUser
}