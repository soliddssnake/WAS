package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.User

class SearchUserViewModel : ViewModel() {
    var userList = mutableStateListOf<User>()
    private val dbUser = Firebase.firestore.collection("users")

    init {
        getUsers()
    }

    fun getUsers(query: String = "") {

        dbUser.whereEqualTo("username", query).get().addOnSuccessListener { documents ->
            for (document in documents) {
                val username = document.get("username") as String
                val name = document.get("name") as String
                val surname = document.get("surname") as String
                val email = document.get("email") as String
                val profilePhotoUrl = document.get("profilePhotoUrl") as String
                val conversations = document.get("conversations") as List<String>

                val user = User(
                    name,
                    surname,
                    username,
                    email = email,
                    profilePhotoUrl = profilePhotoUrl,
                    conversations = conversations
                )
                userList.add(user)
            }
        }
        userList.clear()
    }
}