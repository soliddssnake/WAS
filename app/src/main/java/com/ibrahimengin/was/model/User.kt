package com.ibrahimengin.was.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String = "",
    val surname: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    @get:Exclude
    val password: String = "",
    val gender: String = "",
    val birthday: String = "",
    var fieldQuestion: String? = "",
    val profilePhotoUrl: String? = "https://firebasestorage.googleapis.com/v0/b/wasapp-cf5e9." +
            "appspot.com/o/profilePhotos%2Fuser.png?alt=media&token=ec114d2d-84b2-4ea5-8048-6a78996a26b8",
    val conversations: List<String>? = null,
    @get:Exclude
    var chats: List<Conversation>? = null
) : Parcelable