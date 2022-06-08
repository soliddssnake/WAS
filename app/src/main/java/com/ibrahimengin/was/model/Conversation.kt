package com.ibrahimengin.was.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Conversation(
    @get:Exclude
    var conversationId: String = "",
    val creator: String = "",
    val receiver: String = "",
    val messages: List<Message>? = emptyList()
) : Parcelable
