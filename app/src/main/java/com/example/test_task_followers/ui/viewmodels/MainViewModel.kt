package com.example.test_task_followers.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_task_followers.api.RetrofitClient
import com.example.test_task_followers.data.models.User
import com.example.test_task_followers.data.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    init {
        setAllUsers()
        getAllUsers()
    }

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object  : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("MyTag", t.message.toString())
                }

            })
    }

    private fun setAllUsers() {
        RetrofitClient.apiInstance
            .getAllUser()
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("MyTag", t.message.toString())
                }


            })
    }

//    fun setFollowerCount(username: String) {
//        RetrofitClient.apiInstance
//            .getFollowersCount(username)
//            .enqueue(object : Callback<ArrayList<TestItem>>{
//                override fun onResponse(
//                    call: Call<ArrayList<TestItem>>,
//                    response: Response<ArrayList<TestItem>>
//                ) {
//                    if (response.isSuccessful) {
//                        listFollowers.postValue(response.body())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<TestItem>>, t: Throwable) {
//                    Log.d("MyTag", t.message.toString())
//                }
//            })
//    }

    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }
    private fun getAllUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }
//    fun getFollowerCount(): LiveData<ArrayList<TestItem>> {
//        return listFollowers
//    }
}