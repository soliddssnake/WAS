package com.ibrahimengin.was

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.view.*
import com.ibrahimengin.was.viewmodel.PostListViewModel
import com.ibrahimengin.was.viewmodel.ProfilePostListVM
import com.ibrahimengin.was.viewmodel.SearchUserViewModel
import com.ibrahimengin.was.viewmodel.SharedViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()
    val postListViewModel: PostListViewModel = viewModel()
    val profilePostListVM: ProfilePostListVM = viewModel()
    val searchUserViewModel: SearchUserViewModel = viewModel()
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
            HomeScreen(navController, postListViewModel)
        }
        composable(
            route = ScreenHolder.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(navController)
        }
        composable(
            route = ScreenHolder.SearchScreen.route
        ) {
            SearchScreen()
        }
        composable(
            route = ScreenHolder.ProfileScreen.route
        ) {
            ProfileScreen(navController, postListViewModel, profilePostListVM)
        }
        composable(
            route = ScreenHolder.MessagesScreen.route
        ) {
            MessagesScreen(navController)
        }
        composable(
            route = ScreenHolder.MainScreen.route
        ) {
            MainScreen()
        }
        composable(
            route = ScreenHolder.AddPostScreen.route
        ) {
            AddPostView(navController, postListViewModel)
        }
        composable(
            route = ScreenHolder.SearchUserForChatScreen.route
        ) {
            SearchUserForChatScreen(navController, searchUserViewModel)
        }
    }
}