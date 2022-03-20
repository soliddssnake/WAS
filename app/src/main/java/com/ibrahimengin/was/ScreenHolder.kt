package com.ibrahimengin.was

sealed class ScreenHolder(val route: String){
    object FullName: ScreenHolder(route = "full_name")
}
