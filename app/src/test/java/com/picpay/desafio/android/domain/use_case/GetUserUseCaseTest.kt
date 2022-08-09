package com.picpay.desafio.android.domain.use_case

import com.picpay.desafio.android.UserFactory
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetUserUseCaseTest {
    private val repository = mockk<UserRepository>()
    private val useCase = GetUser(repository)

    @Test
    fun getUsers_return_list_with_success(){
        runBlocking {
            //Given
            coEvery { repository.getUsers() } returns UserFactory.users

            //When
            val result = useCase.getUsers()

            //Then
            Assert.assertEquals(result.size, UserFactory.users.size)

        }
    }

    @Test
    fun getUsers_return_exception(){
        runBlocking {
            //Given
            coEvery { repository.getUsers() } throws RuntimeException()

            //When
            val result = useCase.getUsers()

            //Then
            Assert.assertEquals(result.size,0)

        }
    }
}