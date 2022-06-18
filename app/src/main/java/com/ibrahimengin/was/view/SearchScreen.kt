package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.viewmodel.ChatViewModel
import com.ibrahimengin.was.viewmodel.PostListViewModel
import com.ibrahimengin.was.viewmodel.SearchUserViewModel
import com.ibrahimengin.was.viewmodel.SharedViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchUserViewModel,
    sharedViewModel: SharedViewModel,
    chatViewModel: ChatViewModel
) {
    Scaffold(topBar = {
        GoBackTopBar(navController)
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(hint = "Search ..", modifier = Modifier.padding(12.dp)) {
                viewModel.getUsers(it)
            }
            SearchedUsersListView(
                navController,
                viewModel,
                sharedViewModel,
                chatViewModel,
                ScreenHolder.ProfileScreenForSearch.route
            )
        }
    }
}

@Composable
fun ProfileScreenForSearch(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    postListViewModel: PostListViewModel
) {

    val profilePhotoUrl = sharedViewModel.user!!.profilePhotoUrl
    val pp = rememberAsyncImagePainter(model = profilePhotoUrl)

    Scaffold(topBar = {
        GoBackTopBar(navController)
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp)
        ) {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ProfileImage(pp, 90.dp, 90.dp)
                    Text(
                        sharedViewModel.user!!.name + " " + sharedViewModel.user!!.surname,
                        style = MaterialTheme.typography.body2
                    )
                    Text(sharedViewModel.user!!.username, style = MaterialTheme.typography.caption)
                }
            }
            Spacer(Modifier.height(2.dp))
            Divider()
            SearchedUserPostListLazyView(postListViewModel, sharedViewModel)
        }
    }
}