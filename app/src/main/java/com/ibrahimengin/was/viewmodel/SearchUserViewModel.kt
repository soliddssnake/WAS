package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.User

class SearchUserViewModel : ViewModel() {
    var list = mutableStateOf<List<User>>(listOf())

    var userList = mutableStateListOf<User>()
    private val dbUser = Firebase.firestore.collection("users")
    private var isSearchStarting = true
    private var initializeUserList = mutableStateListOf<User>()
    private var results = mutableStateListOf<User>()

    init {
        getUsers()
    }

    fun getUsers(query: String = "") {

        dbUser.whereEqualTo("username", query).get().addOnSuccessListener { documents ->
            for (document in documents) {
                val username = document.get("username") as String
                val name = document.get("name") as String
                val surname = document.get("surname") as String
                val profilePhotoUrl = document.get("profilePhotoUrl") as String

                val user = User(name, surname, username, profilePhotoUrl = profilePhotoUrl)
                userList.add(user)
            }
        }
        userList.clear()
    }
}