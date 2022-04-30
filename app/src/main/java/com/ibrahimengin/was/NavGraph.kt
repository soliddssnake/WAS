package com.ibrahimengin.was

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.view.*
import com.ibrahimengin.was.viewmodel.SharedViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()
    val currentUser = Firebase.auth.currentUser
    var myStartDestination = ScreenHolder.LoginScreen.route
    if (currentUser != null) {
        myStartDestination = ScreenHolder.HomeScreen.route
    }

    NavHost(navController = navController, startDestination = myStartDestination) {
        composable(
            route = ScreenHolder.LoginScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = ScreenHolder.SignupScreen.route
        ) {
            SignupScreen(navController, sharedViewModel)
        }
        composable(
            route = ScreenHolder.QuestionsScreen.route
        ) {
            QuestionsScreen(navController, sharedViewModel)
        }
        composable(
            route = ScreenHolder.HomeScreen.route
        ) {
            HomeScreen()
        }
        composable(
            route = ScreenHolder.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(navController)
        }
        composable(
            route = ScreenHolder.SearchScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = ScreenHolder.ProfileScreen.route
        ) {
            ProfileScreen()
        }
        composable(
            route = ScreenHolder.MessagesScreen.route
        ) {
            HomeScreen()
        }
        composable(
            route = ScreenHolder.MainScreen.route
        ) {
            MainScreen()
        }
    }
}