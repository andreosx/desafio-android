package com.picpay.desafio.android.presenter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.UserFactory
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.use_case.getUserUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class UserListViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<getUserUseCase>()
    private val viewModel = UserListViewModel(useCase)

    @Before
    fun initMocksAndMainThread() {
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    class MainDispatcherRule(val dispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {

        override fun starting(description: Description?) = Dispatchers.setMain(dispatcher)

        override fun finished(description: Description?) = Dispatchers.resetMain()

    }

    @ExperimentalCoroutinesApi
    @Test
    fun getUsers_return_observable_with_success() = runTest{
            //Given
            coEvery { useCase.getUsers() } returns UserFactory.users

            //When
            viewModel.getUsers()

            //Then
            var allUsers : List<User>? = ArrayList<User>()
            val latch = CountDownLatch(1)
            val observer = object : Observer<List<User>> {
                override fun onChanged(users: List<User>?) {
                    allUsers = users
                    latch.countDown()
                    viewModel.getUserList().removeObserver(this)
                }
            }
            viewModel.getUserList().observeForever(observer)
            assertNotNull(allUsers)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getUsers_return_observable_with_exception() = runTest{
        //Given
        coEvery { useCase.getUsers() } throws RuntimeException()

        //When
        viewModel.getError()

        //Then
        var allError = ""
        val latch = CountDownLatch(1)
        val observer = object : Observer<String> {
            override fun onChanged(error: String) {
                allError = error
                latch.countDown()
                viewModel.getError().removeObserver(this)
            }
        }

        viewModel.getError().observeForever(observer)
        latch.await(10, TimeUnit.SECONDS)
        Assert.assertEquals(allError,"")
    }

}