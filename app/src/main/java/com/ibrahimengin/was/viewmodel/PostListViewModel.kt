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
    var currentUsername = ""
    var currentProfilePhotoUrl = ""
    var currentName = ""
    var currentSurname = ""
    var postList = mutableStateListOf<PostListItem>()
    private val dbPost = Firebase.firestore.collection("posts")
    private val dbUser = Firebase.firestore.collection("users")
    private val _isRefreshing = MutableStateFlow(false)
    private val currentUser = Firebase.auth.currentUser
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        loadPosts()
        getCurrentUserData()
    }

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
        dbPost.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val username = document.get("username") as String
                val explanation = document.get("explanation") as String
                val profilePhotoUrl = document.get("profilePhotoUrl") as String
                val postPhotoDownloadUrl = document.get("postPhotoDownloadUrl") as String?

                val post = PostListItem(username, explanation, profilePhotoUrl, postPhotoDownloadUrl)
                postList.add(post)
            }
        }
    }

    private fun getCurrentUserData() {
        if (currentUser != null) {
            val currentEmail = currentUser.email
            dbUser.document(currentEmail!!).get().addOnSuccessListener {
                currentUsername = it.get("username") as String
                currentProfilePhotoUrl = it.get("profilePhotoUrl") as String
                currentName = it.get("name") as String
                currentSurname = it.get("surname") as String
            }
        }
    }
}
