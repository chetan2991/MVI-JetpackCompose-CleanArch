package com.chetan.mvicomposecleanarch.data

import com.chetan.mvicomposecleanarch.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private fun createOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            //.connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    private fun createGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    val apiService by lazy{
        Retrofit.Builder()
            .client(createOkHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(createGsonConverterFactory())
            .build().create(ApiService::class.java)
    }
}