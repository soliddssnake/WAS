package com.ibrahimengin.was

sealed class ScreenHolder(route: String) {
    object SignupScreen : ScreenHolder(route = "signup_screen")
    object LoginScreen : ScreenHolder(route = "login_screen")
    object QuestionsScreen : ScreenHolder(route = "questions_screen")
}
