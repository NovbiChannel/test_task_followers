package com.example.test_task_followers.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_task_followers.data.api.RetrofitClient
import com.example.test_task_followers.data.models.User
import com.example.test_task_followers.data.models.UserResponse
import com.example.test_task_followers.other.Constants.VALID_TOKEN
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
            .getSearchUsers(query, "token $VALID_TOKEN")
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
            .getAllUser(0,"token $VALID_TOKEN")
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
    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }
    private fun getAllUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }}