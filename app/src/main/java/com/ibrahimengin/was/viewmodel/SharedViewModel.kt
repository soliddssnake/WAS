package com.ibrahimengin.was.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.Conversation
import com.ibrahimengin.was.model.User

class SharedViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    fun addUser(newUser: User) {
        user = newUser
    }

    init {
        getCurrentUser()
    }

    var currentUserData by mutableStateOf<User?>(null)
    fun getCurrentUser() {
        val dbUser = Firebase.firestore.collection("users")
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            dbUser.document(currentUser.email!!).get().addOnSuccessListener {
                currentUserData = it.toObject(User::class.java)
                fillChats()


            }
        }
    }

    fun fillChats() {
        val dbChat = Firebase.firestore.collection("chats")
        dbChat.whereIn(FieldPath.documentId(), currentUserData!!.conversations!!).get().addOnSuccessListener {
            var chats: ArrayList<Conversation> = arrayListOf()

            for (doc in it.documents) {
                val conv = doc.toObject(Conversation::class.java)!!
                conv.conversationId = doc.id
                chats.add(conv)
            }
            currentUserData!!.chats = chats
            Log.d("message : ", currentUserData.toString())
        }
    }

}