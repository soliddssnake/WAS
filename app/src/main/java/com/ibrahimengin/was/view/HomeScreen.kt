package com.ibrahimengin.was.view

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.compose.WASTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ibrahimengin.was.R
import com.ibrahimengin.was.viewmodel.PostListViewModel
import java.util.*

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

    //TODO max line ve max character ayarlanması lazım
    val explanation = remember { mutableStateOf("") }
    val postPhotoDownloadUrl = remember { mutableStateOf("") }
    val profilePhotoUrl = viewModel.currentProfilePhotoUrl
    val pp = rememberAsyncImagePainter(model = profilePhotoUrl)
    val context = LocalContext.current
    val blankWarning = stringResource(R.string.blankWarning)
    val selectedPicture = remember { mutableStateOf<Uri?>(null) }

    val permissionMessage = stringResource(R.string.permissionForGallery)
    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val intentFromResult = result.data
            if (intentFromResult != null) {
                selectedPicture.value = intentFromResult.data
            }
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if (result) {
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        } else {
            Toast.makeText(context, permissionMessage, Toast.LENGTH_LONG).show()
        }
    }

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

                    //TODO image şekli size falan ayarlanması lazım
                    selectedPicture.value?.let {
                        val addedPicture = rememberAsyncImagePainter(model = selectedPicture.value)
                        Box(modifier = Modifier.fillMaxWidth().height(225.dp)) {
                            Image(
                                painter = addedPicture,
                                null,
                                modifier = Modifier.clip(RoundedCornerShape(12.dp)).fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }


                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        IconButton(onClick = {
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(
                                        context as Activity,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                    )
                                ) {
                                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                } else {
                                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                }
                            } else {
                                val intentToGallery =
                                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                                activityResultLauncher.launch(intentToGallery)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Image,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            val uuid = UUID.randomUUID()
                            val postPhotoName = "$uuid.jpg"
                            val db = Firebase.firestore
                            val reference = Firebase.storage.reference
                            val postPhotoReference = reference.child("postPhotos").child(postPhotoName)
                            val postMap = hashMapOf<String, Any>()
                            val username = viewModel.currentUsername

                            if (explanation.value.isNotEmpty()) {

                                if (selectedPicture.value != null) {
                                    postPhotoReference.putFile(selectedPicture.value!!).addOnSuccessListener {
                                        val uploadPhotoReference = reference.child("postPhotos").child(postPhotoName)
                                        uploadPhotoReference.downloadUrl.addOnSuccessListener {
                                            postPhotoDownloadUrl.value = it.toString()
                                            postMap["explanation"] = explanation.value
                                            postMap["username"] = username
                                            postMap["profilePhotoUrl"] = profilePhotoUrl
                                            postMap["postPhotoDownloadUrl"] = postPhotoDownloadUrl.value


                                            db.collection("posts").add(postMap).addOnSuccessListener {
                                                navController.popBackStack()
                                            }.addOnFailureListener {
                                                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                                            }
                                        }

                                    }.addOnFailureListener {
                                        Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                                    }

                                } else {
                                    postMap["explanation"] = explanation.value
                                    postMap["username"] = username
                                    postMap["profilePhotoUrl"] = profilePhotoUrl

                                    db.collection("posts").add(postMap).addOnSuccessListener {
                                        navController.popBackStack()
                                    }.addOnFailureListener {
                                        Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                                    }
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