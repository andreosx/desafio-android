package com.picpay.desafio.android.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.model.toUser
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User
import io.reactivex.plugins.RxJavaPlugins.onError
import kotlinx.coroutines.*

class UserListViewModel constructor(private val repository: UserRepository)  : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    fun getUsers() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userList.postValue(response.body()?.map { it.toUser() })
                } else {
                    errorMessage.postValue("Error : ${response.message()} ")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}