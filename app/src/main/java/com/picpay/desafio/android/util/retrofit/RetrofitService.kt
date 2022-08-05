package com.picpay.desafio.android.util.retrofit

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun getInstance(): PicPayApi{
        return Retrofit.Builder()
            .baseUrl(Constants.url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PicPayApi::class.java)
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }
}