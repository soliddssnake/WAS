package com.ibrahimengin.was

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrahimengin.was.view.LoginScreen
import com.ibrahimengin.was.view.MainScreen
import com.ibrahimengin.was.view.QuestionsScreen
import com.ibrahimengin.was.view.SignupScreen
import com.ibrahimengin.was.viewmodel.SharedViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = ScreenHolder.LoginScreen.toString()) {
        composable(
            route = ScreenHolder.LoginScreen.toString()
        ) {
            LoginScreen(navController)
        }
        composable(
            route = ScreenHolder.SignupScreen.toString()
        ) {
            SignupScreen(navController, sharedViewModel)
        }
        composable(
            route = ScreenHolder.QuestionsScreen.toString()
        ) {
            QuestionsScreen(navController, sharedViewModel)
        }
        composable(
            route = ScreenHolder.MainScreen.toString()
        ) {
            MainScreen(navController)
        }
    }
}