package com.example.test_task_followers.app

import android.app.Application
import com.example.test_task_followers.network.Networking
import timber.log.Timber

class GhusApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Networking.init(this)
        Timber.plant(Timber.DebugTree())
    }
}