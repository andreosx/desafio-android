package com.picpay.desafio.android.presenter.activity

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.presenter.adapter.UserListAdapter
import com.picpay.desafio.android.presenter.viewmodel.UserListViewModel
import com.picpay.desafio.android.presenter.viewmodel.factory.UserViewModelFactory
import com.picpay.desafio.android.util.retrofit.RetrofitService

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: UserListViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProvider(this, UserViewModelFactory(UserRepository(retrofitService))).get(UserListViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        showLoading()
        callObservable()
        viewModel.getUsers()
    }

    private fun callObservable(){
        viewModel.userList.observe(this, Observer {
            hiddenLoading()
            adapter.users = it
        })

        viewModel.errorMessage.observe(this, Observer {
            hiddenLoading()
            recyclerView.visibility = View.GONE
            Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
        })
    }

    private fun showLoading(){
        progressBar.visibility = View.VISIBLE
    }

    private fun hiddenLoading(){
        progressBar.visibility = View.GONE
    }
}
