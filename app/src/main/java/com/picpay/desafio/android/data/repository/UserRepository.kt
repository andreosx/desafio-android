package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.model.toUser
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.Output
import com.picpay.desafio.android.util.parseResponse

class UserRepositoryImpl(
    private val api: PicPayApi
): UserRepository {

    override suspend fun getUsers(): List<User> {
        return when(api.getUsers().parseResponse()){
            is Output.Success -> api.getUsers().body()!!.map { it.toUser() }
            is Output.Failure -> throw Exception()
        }
    }
}
interface UserRepository {
    suspend fun getUsers(): List<User>
}