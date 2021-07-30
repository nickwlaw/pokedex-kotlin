package com.nickwlaw.pokedex.data.repositories

import com.nickwlaw.pokedex.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    private val httpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun<T> buildApi(api: Class<T>): T{
        return retrofit.create(api)
    }
}