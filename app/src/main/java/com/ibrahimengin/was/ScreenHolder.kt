package com.ibrahimengin.was

sealed class ScreenHolder(route: String) {
    object SignupScreen : ScreenHolder(route = "signup_screen")
    object LoginScreen : ScreenHolder(route = "login_screen")
    object QuestionsScreen : ScreenHolder(route = "questions_screen")
    object MainScreen : ScreenHolder(route = "main_screen")
    object ForgotPasswordScreen : ScreenHolder(route = "forgot_password_screen")
}
