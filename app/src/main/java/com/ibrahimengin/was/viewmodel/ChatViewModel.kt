package com.ibrahimengin.was.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.model.Conversation

class ChatViewModel : ViewModel() {

    var conversationId by mutableStateOf("")
        private set

    var currentChat by mutableStateOf(Conversation())
        private set


    fun addConversation(newConversation: String) {
        conversationId = newConversation
    }

    fun addCurrentChat(newChat: Conversation) {
        currentChat = newChat
    }

    //TODO açılınca conversationId ile dinle değişiklik olunca clearlayıp update
    fun updateChat() {
        Firebase.firestore.collection("chats").document(conversationId).addSnapshotListener { snapshot, error ->
            if (snapshot != null && snapshot.exists()) {
                currentChat = snapshot.toObject(Conversation::class.java)!!
            }
            Log.d("Chat : ", currentChat.toString())
        }
    }
}
