package com.ibrahimengin.was.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val message: String = "",
    val sender: String = "",
    val time: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now()
) : Parcelable
