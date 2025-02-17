package com.architecture.mvvmarchitecture.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val BASE_URL = "base url for your api"
        private const val STATIC_BEARER_TOKEN = "if you have static token"

        fun getRetrofitInstance():Retrofit{
            // Create an OkHttpClient with an interceptor to add the Authorization header
            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .addHeader("Authorization", "Bearer $STATIC_BEARER_TOKEN")
                        .build()
                    chain.proceed(request)
                })
                .build()

            // Build and return the Retrofit instance
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}