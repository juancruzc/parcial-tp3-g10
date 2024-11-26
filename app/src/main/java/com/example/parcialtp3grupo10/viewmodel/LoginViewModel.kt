package com.example.parcialtp3grupo10.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.parcialtp3grupo10.client.ApiService
import com.example.parcialtp3grupo10.client.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun loginUser(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val call = apiService.login(username, password)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.token?.let {
                        Log.d("Login", "Token: $it")
                        onSuccess()
                    } ?: onError("Token no encontrado")
                } else {
                    onError("Login fallido")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError("Error de conexión: ${t.message}")
            }
        })
    }
}