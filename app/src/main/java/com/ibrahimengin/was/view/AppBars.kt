package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

@Composable
fun MoreVertButton(
    navController: NavController
) {
    val openDialog = remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    val auth = Firebase.auth

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    auth.signOut()
                    navController.navigate(ScreenHolder.LoginScreen.route) {
                        popUpTo(ScreenHolder.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }) { Text(stringResource(R.string.logout)) }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false })
                { Text(stringResource(R.string.cancel)) }
            },
            title = { Text(stringResource(R.string.logout)) },
            text = { Text(stringResource(R.string.areYouSureLogOut)) }
        )
    }
    Column {
        IconButton(onClick = { expanded.value = true }) {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = stringResource(R.string.more))
        }
        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                Text(stringResource(R.string.editProfile))
            }
            Divider()
            DropdownMenuItem(onClick = {
                openDialog.value = true
            }) {
                Text(stringResource(R.string.logout))
            }
        }
    }
}