package com.ibrahimengin.was.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.compose.WASTheme
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.viewmodel.PostListViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: PostListViewModel) {

    val profilePhotoUrl = viewModel.profilePhotoUrl
    val pp = rememberAsyncImagePainter(model = profilePhotoUrl)

    Scaffold(topBar = {
        ProfileScreenTopBar(navController)
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp)
        ) {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (profilePhotoUrl.isNotEmpty()) {
                        Image(
                            painter = pp,
                            stringResource(R.string.profilePhoto),
                            modifier = Modifier.size(width = 90.dp, height = 90.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.was_logo),
                            stringResource(R.string.profilePhoto),
                            modifier = Modifier.size(width = 90.dp, height = 90.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(viewModel.name + " " + viewModel.surname, style = MaterialTheme.typography.body2)
                    Text(viewModel.username, style = MaterialTheme.typography.caption)
                }
            }
        }
    }
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

@Preview
@Composable
fun PreviewProfileScreen() {
    WASTheme {
        ProfileScreen(rememberNavController(), PostListViewModel())
    }
}