package com.ibrahimengin.was

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenHolder(
    val route: String,
    val icon: ImageVector? = Icons.Default.Home
) {
    object SignupScreen : ScreenHolder(route = "signup_screen")
    object LoginScreen : ScreenHolder(route = "login_screen")
    object QuestionsScreen : ScreenHolder(route = "questions_screen")
    object MainScreen : ScreenHolder(route = "main_screen")
    object HomeScreen : ScreenHolder(route = "home_screen", icon = Icons.Filled.Home)
    object ForgotPasswordScreen : ScreenHolder(route = "forgot_password_screen")
    object SearchScreen : ScreenHolder(route = "search_screen", icon = Icons.Filled.Search)
    object ProfileScreen : ScreenHolder(route = "profile_screen", icon = Icons.Filled.Person)
    object MessagesScreen : ScreenHolder(route = "messages_screen", icon = Icons.Filled.Message)
}
