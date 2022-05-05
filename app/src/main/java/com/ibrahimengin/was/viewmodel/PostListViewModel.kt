package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.PostListItem

class PostListViewModel : ViewModel() {
    var username = ""
    var postList = mutableStateListOf<PostListItem>()
    private val db = Firebase.firestore.collection("posts")

    init {
        loadPosts()
        getUsername()
    }

    fun loadPosts() {
        db.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val username = document.get("username") as String
                val explanation = document.get("explanation") as String

                val post = PostListItem(username, explanation)
                postList.add(post)
            }
        }
    }

    private fun getUsername() {
        val currentEmail = Firebase.auth.currentUser!!.email
        Firebase.firestore.collection("users").document(currentEmail!!).get().addOnSuccessListener {
            username = it.get("username") as String
        }
    }
}
