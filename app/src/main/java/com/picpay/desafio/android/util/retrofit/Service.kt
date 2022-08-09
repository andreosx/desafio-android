package com.picpay.desafio.android.util.retrofit

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.url)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun <API> createService(apiClass: Class<API>): API {
        return retrofit.create(apiClass)
    }
}