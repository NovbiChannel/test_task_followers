package com.example.test_task_followers.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_task_followers.data.api.RetrofitClient
import com.example.test_task_followers.data.models.DetailUserResponce
import com.example.test_task_followers.data.models.ReposUserResponce
import com.example.test_task_followers.other.Constants
import com.example.test_task_followers.other.Constants.VALID_TOKEN
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Time
import java.util.concurrent.TimeUnit

class DetailUserViewModel: ViewModel() {
    val user = MutableLiveData<DetailUserResponce>()
    val repo = MutableLiveData<ArrayList<ReposUserResponce>>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username, "token ${VALID_TOKEN}")
            .enqueue(object : Callback<DetailUserResponce>{
                override fun onResponse(
                    call: Call<DetailUserResponce>,
                    response: Response<DetailUserResponce>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<DetailUserResponce>, t: Throwable) {
                    Log.d("MyTag", t.message.toString())
                }
            })
    }

    fun getUserDetail(): MutableLiveData<DetailUserResponce> {
        return user
    }

    fun setReposUser(username: String) {
        RetrofitClient.apiInstance
            .getReposUser(username, "token ${VALID_TOKEN}")
            .enqueue(object : Callback<ArrayList<ReposUserResponce>>{
                override fun onResponse(
                    call: Call<ArrayList<ReposUserResponce>>,
                    response: Response<ArrayList<ReposUserResponce>>
                ) {
                    if (response.isSuccessful) {
                        repo.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<ReposUserResponce>>, t: Throwable) {
                    Log.d("MyTag", t.message.toString())
                }
            })
    }

    fun getReposUser(): LiveData<ArrayList<ReposUserResponce>> {
        return repo
    }
}