package com.ibrahimengin.was

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.view.LoginScreen
import com.ibrahimengin.was.view.SignupScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenHolder.LoginScreen.toString()) {
        composable(
            route = ScreenHolder.LoginScreen.toString()
        ) {
            LoginScreen(navController)
        }
        composable(
            route = ScreenHolder.SignupScreen.toString()
        ) {
            SignupScreen(navController, Firebase.firestore)
        }
    }
}