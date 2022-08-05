package com.picpay.desafio.android.presenter.viewmodel

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.repository.UserRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserListViewModelTest {
    lateinit var userRepository: UserRepository
    lateinit var userListViewModel: UserListViewModel
    @Mock
    lateinit var apiApi: PicPayApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(apiApi)
        userListViewModel = UserListViewModel(userRepository)
    }

//    @Test
//    fun getUsersTest() {
//        runBlocking {
//            Mockito.`when`(userRepository.getUsers()).thenReturn(Response.success(listOf<User>(User("movie", "", "new"))))
//            userListViewModel.getUsers()
//            val result = userListViewModel.movieList.getOrAwaitValue()
//            assertEquals(listOf<User>(User("movie", "", "new")), result)
//        }
//    }
}