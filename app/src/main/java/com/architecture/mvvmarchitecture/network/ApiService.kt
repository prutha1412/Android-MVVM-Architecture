package com.architecture.mvvmarchitecture.network

import com.architecture.mvvmarchitecture.model.Login
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // Add your API end points with model response and method
    // Sample
    @POST("login") // add own api endpoint
    suspend fun login(@Body params:HashMap<String, String>): Login
}