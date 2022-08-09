package com.picpay.desafio.android.domain.use_case

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User

class GetUser(private val repository: UserRepository): getUserUseCase {

    override suspend fun getUsers(): List<User> = try {
        repository.getUsers()
    } catch (ex: Exception){
        listOf()
    }

}
interface getUserUseCase{
    suspend fun getUsers(): List<User>
}
