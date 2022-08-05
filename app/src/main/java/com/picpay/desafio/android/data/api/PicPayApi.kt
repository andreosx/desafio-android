package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface PicPayApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDTO>>
}