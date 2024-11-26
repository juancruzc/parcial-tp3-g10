package com.example.parcialtp3grupo10

import android.app.Application
import android.os.Bundle
import com.example.parcialtp3grupo10.client.ApiService
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var apiService: ApiService
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}