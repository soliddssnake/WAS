package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.PostListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel : ViewModel() {
    var username = ""
    var postList = mutableStateListOf<PostListItem>()
    private val db = Firebase.firestore.collection("posts")

    init {
        loadPosts()
        getUsername()
    }

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refresh(myList: SnapshotStateList<PostListItem>) {
        viewModelScope.launch {
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            delay(2000)
            _isRefreshing.emit(false)
            myList.swapList(getPostListItem())
        }
    }

    private fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
        clear()
        addAll(newList)
    }

    private fun getPostListItem(): List<PostListItem> {
        loadPosts()
        return postList
    }

    private fun loadPosts() {
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
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            val currentEmail = currentUser.email
            Firebase.firestore.collection("users").document(currentEmail!!).get().addOnSuccessListener {
                username = it.get("username") as String
            }
        }
    }
}
