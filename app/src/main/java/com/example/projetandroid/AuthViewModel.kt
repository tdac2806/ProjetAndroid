package com.example.projetandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private var _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    init {
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun setLogin(login : String) {
        _login.value = login
    }

}