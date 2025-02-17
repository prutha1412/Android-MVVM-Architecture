package com.architecture.mvvmarchitecture.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.mvvmarchitecture.repository.repository


@Suppress("UNCHECKED_CAST")
class viewModelFactory(private val repository: repository, private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewmodel(repository,context) as T
    }
}