package com.picpay.desafio.android

import com.picpay.desafio.android.data.api.PicPayApi
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.use_case.GetUser
import com.picpay.desafio.android.domain.use_case.getUserUseCase
import com.picpay.desafio.android.presenter.viewmodel.UserListViewModel
import com.picpay.desafio.android.util.retrofit.Service
import org.koin.dsl.module

val picPayServiceModule = module {

    single { Service().createService(PicPayApi::class.java) }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single<getUserUseCase> { GetUser(get()) }

    single { UserListViewModel(get()) }
}

