package com.example.parcialtp3grupo10.client
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object RetrofitInstance {

    // Crea un interceptor de logging para ver las solicitudes en el logcat
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Crea un cliente HTTP usando OkHttp
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Instancia de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")  // Asegúrate de usar la URL correcta
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())  // Usa Gson para convertir JSON
            .build()
    }

    // Crea la interfaz ApiService usando Retrofit
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
