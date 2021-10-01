package com.example.projetandroid


import android.util.Log
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {


    init {
        Log.d("GameFragment", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

}