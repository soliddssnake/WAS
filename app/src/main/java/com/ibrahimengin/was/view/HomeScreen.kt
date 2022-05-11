package com.ibrahimengin.was.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.compose.WASTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R
import com.ibrahimengin.was.viewmodel.PostListViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: PostListViewModel) {
    Scaffold(topBar = {
        HomeScreenTopBar(navController)
    }) {
        PostListLazyView(viewModel)
    }
}

@Composable
fun AddPostView(navController: NavController, viewModel: PostListViewModel) {

    val explanation = remember { mutableStateOf("") }
    val profilePhotoUrl = viewModel.currentProfilePhotoUrl
    val pp = rememberAsyncImagePainter(model = profilePhotoUrl)
    val context = LocalContext.current
    val blankWarning = stringResource(R.string.blankWarning)

    Scaffold(topBar = { GoBackTopBar(navController) }) {
        Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp).padding(horizontal = 5.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ProfileImage(pp, 50.dp, 50.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        explanation.value,
                        { explanation.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(stringResource(R.string.shareWithUs)) },
                        shape = MaterialTheme.shapes.medium
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        IconButton(onClick = {}) {//TODO
                            Icon(
                                imageVector = Icons.Outlined.Image,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            val db = Firebase.firestore
                            val postMap = hashMapOf<String, Any>()
                            val username = viewModel.currentUsername
                            val profilePhotoUrl = viewModel.currentProfilePhotoUrl

                            postMap["explanation"] = explanation.value
                            postMap["username"] = username
                            postMap["profilePhotoUrl"] = profilePhotoUrl

                            if (explanation.value.isNotEmpty()) {
                                db.collection("posts").add(postMap).addOnSuccessListener {
                                    navController.popBackStack()
                                }.addOnFailureListener {
                                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                                }
                            } else {
                                Toast.makeText(context, blankWarning, Toast.LENGTH_LONG).show()
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    WASTheme {
//    HomeScreen(navController = rememberNavController(), PostListViewModel())
//    HomeScreenTopBar(navController = rememberNavController())
//        AddPostView(navController = rememberNavController())
    }
}