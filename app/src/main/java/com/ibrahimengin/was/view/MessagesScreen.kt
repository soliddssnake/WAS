package com.ibrahimengin.was.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.model.User
import com.ibrahimengin.was.viewmodel.SearchUserViewModel


@Composable
fun MessagesScreen(navController: NavController) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(ScreenHolder.SearchUserForChatScreen.route) }) {
            Icon(Icons.Filled.Add, null)
        }
    }) {

    }
}

@Composable
fun SearchUserForChatScreen(navController: NavController, viewModel: SearchUserViewModel) {
    Scaffold(topBar = {
        GoBackTopBar(navController)
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(hint = "Search ..", modifier = Modifier.padding(12.dp)) {
                viewModel.getUsers(it)
            }
            SearchedUsersListView(navController, viewModel)
        }
    }
}

@Composable
fun SearchedUsersListView(navController: NavController, viewModel: SearchUserViewModel) {

    val users = remember { viewModel.userList }

    LazyColumn(contentPadding = PaddingValues(vertical = 5.dp)) {
        items(users) { user ->
            SearchUserRow(navController, user)
        }
    }
}

@Composable
fun SearchUserRow(navController: NavController, user: User) {

    val pp = rememberAsyncImagePainter(model = user.profilePhotoUrl)

    Row(modifier = Modifier.fillMaxWidth()
        .clickable {
            //TODO
        }) {
        ProfileImage(pp, 40.dp, 40.dp)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = user.name + " " + user.surname, style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = user.username, style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, hint: String = "", onSearch: (String) -> Unit = {}) {
    val text = remember { mutableStateOf("") }
    val isHintDisplayed = remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = text.value, onValueChange = {
                text.value = it
                onSearch(it)
            }, modifier = Modifier.fillMaxWidth()
                .shadow(3.dp, CircleShape)
                .onFocusChanged {
                    isHintDisplayed.value = it.isFocused != true && text.value.isEmpty()
                },
            singleLine = true,
            shape = CircleShape
        )
        if (isHintDisplayed.value) {
            Text(text = hint, modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp))
        }
    }

}