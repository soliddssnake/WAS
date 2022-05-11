package com.ibrahimengin.was.view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder

@Composable
fun HomeScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h3)
        }, actions = {
            IconButton(onClick = {
                navController.navigate(ScreenHolder.AddPostScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Outlined.AddBox, contentDescription = stringResource(R.string.addPost)
                )
            }

//            IconButton(onClick = {
//
//            }) {
//                Icon(
//                    imageVector = Icons.Filled.Notifications,
//                    contentDescription = stringResource(R.string.notifications)
//                )
//            }
        }
    )
}

@Composable
fun GoBackTopBar(navController: NavController) {
    TopAppBar(
        title = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.goBack)
                )
            }
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h3)
        }
    )
}

@Composable
fun ProfileScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h3)
        }, actions = {
            IconButton(onClick = {
                navController.navigate(ScreenHolder.AddPostScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Outlined.AddBox, contentDescription = stringResource(R.string.addPost)
                )
            }
            MoreVertButton(navController)
        }
    )
}