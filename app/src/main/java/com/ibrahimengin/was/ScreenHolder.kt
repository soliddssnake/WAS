package com.ibrahimengin.was

sealed class ScreenHolder(route: String){
    object FullName: ScreenHolder(route = "full_name")
}
