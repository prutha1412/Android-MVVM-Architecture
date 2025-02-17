package com.architecture.mvvmarchitecture

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.architecture.mvvmarchitecture.databinding.ActivityMainBinding
import com.architecture.mvvmarchitecture.network.ApiService
import com.architecture.mvvmarchitecture.network.Resource
import com.architecture.mvvmarchitecture.network.RetrofitInstance
import com.architecture.mvvmarchitecture.repository.repository
import com.architecture.mvvmarchitecture.viewmodel.viewModelFactory
import com.architecture.mvvmarchitecture.viewmodel.viewmodel

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: viewmodel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        val apiService = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val repository = repository(apiService)
        mainViewModel = ViewModelProvider(this, viewModelFactory(repository, this)).get(viewmodel::class.java)
        binding.btnLogin.setOnClickListener{
            handleLogin()
        }
        mainViewModel.login.observe(this, Observer { response ->
            when(response){
                is Resource.Loading->{}
                is Resource.Success->{}
                is Resource.Error->{}
            }
        })

    }

    private fun handleLogin() {
        val email =binding.edtEmail.text
        val password = binding.edtPassword.text
        var map = HashMap<String,String>()
        map.put("email", email.toString())
        map.put("password",password.toString())
        mainViewModel.getLogin(map)
    }
}