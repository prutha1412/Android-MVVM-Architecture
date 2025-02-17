package com.architecture.mvvmarchitecture.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architecture.mvvmarchitecture.model.Login
import com.architecture.mvvmarchitecture.network.Resource
import com.architecture.mvvmarchitecture.repository.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewmodel(val repository: repository, val context:Context):ViewModel() {
    // write your view model functions here

    private val _login = MutableLiveData<Resource<Login>>()
    val login:LiveData<Resource<Login>>
        get() = _login
    fun getLogin(params: HashMap<String,String>){
        viewModelScope.launch (Dispatchers.IO){
            try{
                val response = repository.login(params)
                _login.postValue(Resource.Success(response))
            }catch (e:Exception){
                Log.e("viewmodel","Error in login")
                _login.postValue(Resource.Error(e.toString()))
            }
        }
    }
}