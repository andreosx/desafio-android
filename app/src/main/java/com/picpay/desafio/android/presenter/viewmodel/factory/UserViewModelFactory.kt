package com.picpay.desafio.android.presenter.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.presenter.viewmodel.UserListViewModel

class UserViewModelFactory constructor(private val repository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            UserListViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}