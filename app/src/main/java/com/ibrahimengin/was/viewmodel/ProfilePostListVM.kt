package com.ibrahimengin.was.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
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

class ProfilePostListVM : ViewModel() {
    private var currentUsername = ""
    var myPostList = mutableStateListOf<PostListItem>()
    var userPostList: MutableLiveData<PostListItem>
    private val dbPost = Firebase.firestore.collection("posts")
    private val dbUser = Firebase.firestore.collection("users")
    private val _isRefreshing = MutableStateFlow(false)
    private val currentUser = Firebase.auth.currentUser
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        loadPosts()
        userPostList = MutableLiveData()
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

    private fun loadPosts() {

        if (currentUser != null) {
            val currentEmail = currentUser.email
            dbUser.document(currentEmail!!).get().addOnSuccessListener {
                currentUsername = it.get("username") as String
                dbPost.whereEqualTo("username", currentUsername).get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        val myUsername = document.get("username") as String
                        val myExplanation = document.get("explanation") as String
                        val myProfilePhotoUrl = document.get("profilePhotoUrl") as String
                        val myPostPhotoDownloadUrl = document.get("postPhotoDownloadUrl") as String?

                        val myPost = PostListItem(myUsername, myExplanation, myProfilePhotoUrl, myPostPhotoDownloadUrl)
                        myPostList.add(myPost)
                    }
                }
            }
        }
    }

    private fun getPostListItem(): List<PostListItem> {
        loadPosts()
        return myPostList
    }

}