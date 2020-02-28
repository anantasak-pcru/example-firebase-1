package com.mengxyz.example.firebase_admin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mengxyz.example.firebase_admin.db.repository.AdminRepository
import com.mengxyz.example.firebase_admin.db.response.User
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val repository = AdminRepository()

    val allUser: MutableLiveData<Array<User>> = MutableLiveData()

    val response: MutableLiveData<String> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getAllUer()
    }

    fun getAllUer() = viewModelScope.launch {
        isLoading(true)
        val data = repository.getAllUser()
        allUser.postValue(data)
        isLoading.postValue(false)
    }

    fun createUser(email: String, password: String, token: String) = viewModelScope.launch {
        isLoading(true)
        val res = repository.createUser(email, password, token)
        response(res)
    }

    fun createAnonymous() = viewModelScope.launch {
        isLoading(true)
        val res = repository.createAnonymous()
        response(res)
    }

    fun updateUser() = viewModelScope.launch {
        isLoading(true)
        val res = repository.updateUser("QDxTMv9AtoPeckvoC7ITp6egyfE2")
        response(res)
    }

    private fun response(res:String){
        response.postValue(res)
        isLoading(false)
    }

    private fun isLoading(status: Boolean) {
        isLoading.postValue(status)
    }
}