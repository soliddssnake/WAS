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
    object ForgotPasswordScreen : ScreenHolder(route = "forgot_password_screen")
    object SearchUserForChatScreen : ScreenHolder(route = "search_user_for_chat_screen")
    object ChatScreen : ScreenHolder(route = "chat_screen")

    //BottomNavigation
    object HomeScreen : ScreenHolder(route = "home_screen", icon = Icons.Filled.Home)
    object SearchScreen : ScreenHolder(route = "search_screen", icon = Icons.Filled.Search)
    object ProfileScreen : ScreenHolder(route = "profile_screen", icon = Icons.Filled.Person)
    object MessagesScreen : ScreenHolder(route = "messages_screen", icon = Icons.Filled.Message)

    object AddPostScreen : ScreenHolder(route = "add_post_screen")
}
