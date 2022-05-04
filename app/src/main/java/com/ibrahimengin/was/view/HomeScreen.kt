package com.ibrahimengin.was.view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimengin.was.R
import com.ibrahimengin.was.viewmodel.PostListViewModel

@Composable
fun HomeScreen(viewModel: PostListViewModel) {
    Scaffold(topBar = {
        HomeScreenTopBar()
    }) {
        PostListLazyView(viewModel)
    }
}


@Composable
fun HomeScreenTopBar() {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h3)
        }, actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.AddBox, contentDescription = stringResource(R.string.addPost)
                )
            }

            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = stringResource(R.string.notifications)
                )
            }
        }
    )
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(PostListViewModel())
}