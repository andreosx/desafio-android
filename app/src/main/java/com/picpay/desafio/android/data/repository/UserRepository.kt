package com.picpay.desafio.android.data.repository

import android.icu.util.Output
import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.model.UserDTO
import com.picpay.desafio.android.domain.model.User
import retrofit2.Response

class UserRepository(
    private val retrofitApi: PicPayApi
){

    suspend fun getUsers(): Response<List<UserDTO>> {
        return retrofitApi.getUsers()
    }

    interface UserRepository{
        suspend fun getUsers(): List<User>
    }

}