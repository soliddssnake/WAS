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
    val profilePhotoUrl: String? = ""
) : Parcelable