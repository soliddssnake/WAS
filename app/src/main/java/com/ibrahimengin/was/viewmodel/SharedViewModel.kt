package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ibrahimengin.was.model.User

class SharedViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    fun addUser(newUser: User) {
        user = newUser
    }
}