package com.architecture.mvvmarchitecture.repository

import com.architecture.mvvmarchitecture.model.Login
import com.architecture.mvvmarchitecture.network.ApiService

class repository(private val apiService:ApiService) {
    // call your api from this repo

    suspend fun login(params:HashMap<String, String>): Login{
        return apiService.login(params)
    }
}