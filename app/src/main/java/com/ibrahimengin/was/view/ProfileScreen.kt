package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.compose.WASTheme
import com.ibrahimengin.was.viewmodel.PostListViewModel
import com.ibrahimengin.was.viewmodel.ProfilePostListVM

@Composable
fun ProfileScreen(
    navController: NavController,
    postViewModel: PostListViewModel,
    profilePostListVM: ProfilePostListVM
) {

    val profilePhotoUrl = postViewModel.currentProfilePhotoUrl
    val pp = rememberAsyncImagePainter(model = profilePhotoUrl)

    Scaffold(topBar = {
        ProfileScreenTopBar(navController)
    }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp)
        ) {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ProfileImage(pp, 90.dp, 90.dp)
                    Text(
                        postViewModel.currentName + " " + postViewModel.currentSurname,
                        style = MaterialTheme.typography.body2
                    )
                    Text(postViewModel.currentUsername, style = MaterialTheme.typography.caption)
                }
            }
            Spacer(Modifier.height(2.dp))
            Divider()
            CurrentPostListLazyView(profilePostListVM)
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    WASTheme {
        ProfileScreen(rememberNavController(), PostListViewModel(), ProfilePostListVM())
    }
}