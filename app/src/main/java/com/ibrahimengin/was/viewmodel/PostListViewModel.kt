package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.PostListItem

class PostListViewModel : ViewModel() {
    var postList = mutableStateListOf<PostListItem>()
        private set
    private val db = Firebase.firestore.collection("posts")

    init {
        loadPosts()
    }

    private fun loadPosts() {
        db.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val username = document.get("username") as String

                val post = PostListItem(username)
                postList.add(post)
            }
        }
    }
}
