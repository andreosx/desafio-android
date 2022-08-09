package com.picpay.desafio.android.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.use_case.getUserUseCase
import kotlinx.coroutines.*

class UserListViewModel (private val getUserUseCase: getUserUseCase)  : ViewModel() {

    private val userList = MutableLiveData<List<User>>()
    private val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    fun getUsers() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getUserUseCase.getUsers()
            withContext(Dispatchers.Main) {
                if(response.isNotEmpty()){
                    userList.postValue(response)
                }else{
                    errorMessage.postValue("Erro")
                }
            }
        }
    }

    fun getUserList(): LiveData<List<User>> {
        return userList
    }

    fun getError(): LiveData<String> {
        return errorMessage
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}