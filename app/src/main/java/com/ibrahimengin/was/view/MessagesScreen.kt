package com.ibrahimengin.was.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.model.Message
import com.ibrahimengin.was.model.User
import com.ibrahimengin.was.viewmodel.ChatViewModel
import com.ibrahimengin.was.viewmodel.SearchUserViewModel
import com.ibrahimengin.was.viewmodel.SharedViewModel


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
fun ChatScreen(sharedViewModel: SharedViewModel, chatViewModel: ChatViewModel) {

    val text = remember { mutableStateOf("") }
    val list = mutableStateListOf<Message>()
    val receiver = mutableStateListOf<Message>()
    //chatViewModel.currentChat.messages!!.forEach { list.add(it) }
    list.add(Message("Naber?"))
    list.add(Message("İyi senden?"))
    list.add(Message("İyi, Haftaya tedavi gören hastalarla etkinlik yapacağız sen de gelmek ister misin?"))
    list.add(Message("Tabiki isterim"))
    list.add(Message("Konum ve zamanı ileteceğim."))
    list.add(Message("Teşekkürler"))
    Scaffold(
        //TODO top bar
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.weight(weight = 1f, fill = true),
                contentPadding = PaddingValues(vertical = 2.dp)
            ) {
                items(list) { message ->
                    SenderMessageRow(message)
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(value = text.value, onValueChange = { text.value = it }, modifier = Modifier.weight(1f, true))
                IconButton(onClick = {
                    val currentEmail = Firebase.auth.currentUser!!.email!!
                    val accessSVM = sharedViewModel.user!!
                    val messageMap = hashMapOf<String, Any>()
                    val dbChat = Firebase.firestore.collection("chats")
                    val timestamp = com.google.firebase.Timestamp.now()
                    messageMap["creator"] = currentEmail
                    messageMap["receiver"] = accessSVM.email
                    messageMap["messages"] = accessSVM.email
                    val newMessage = Message(text.value, currentEmail, timestamp)
                    //TODO empty message
                    dbChat.document(chatViewModel.conversationId).update("messages", FieldValue.arrayUnion(newMessage))
                    text.value = ""
                }) {
                    Icon(Icons.Filled.Send, null)
                }
            }
        }
    }
}

@Composable
fun SenderMessageRow(message: Message) {
    Box(modifier = Modifier.padding(5.dp).background(Color.Cyan, CircleShape)) {
        Text(message.message, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ReceiverMessageRow(message: Message) {
    Box(modifier = Modifier.padding(5.dp).background(Color.LightGray, CircleShape)) {
        Text(message.message, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun SearchUserForMessagesScreen(
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
                ScreenHolder.ChatScreen.route
            )
        }
    }
}

@Composable
fun SearchedUsersListView(
    navController: NavController,
    viewModel: SearchUserViewModel,
    sharedViewModel: SharedViewModel,
    chatViewModel: ChatViewModel,
    navigationRoute: String
) {

    val users = remember { viewModel.userList }

    LazyColumn(contentPadding = PaddingValues(vertical = 5.dp)) {
        items(users) { user ->
            SearchUserRow(navController, user, sharedViewModel, chatViewModel, navigationRoute)
        }
    }
}

@Composable
fun SearchUserRow(
    navController: NavController,
    user: User,
    sharedViewModel: SharedViewModel,
    chatViewModel: ChatViewModel,
    navigationRoute: String
) {

    val pp = rememberAsyncImagePainter(model = user.profilePhotoUrl)
    val currentMail = Firebase.auth.currentUser!!.email!!

    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable {

                sharedViewModel.addUser(
                    User(
                        name = user.name,
                        surname = user.surname,
                        email = user.email,
                        username = user.username,
                        profilePhotoUrl = user.profilePhotoUrl,
                        conversations = user.conversations
                    )
                )


                val accessSVM = sharedViewModel.user!!
                if (sharedViewModel.currentUserData?.conversations?.isNotEmpty() == true && accessSVM.conversations?.isNotEmpty() == true) {
                    for (myConversation in sharedViewModel.currentUserData!!.conversations!!) {
                        for (urConversation in accessSVM.conversations) {
                            if (myConversation == urConversation) {
                                chatViewModel.addConversation(myConversation)
                            }
                        }
                    }
                }




                navController.navigate(navigationRoute)//Chat Screen
            }) {
        ProfileImage(pp, 40.dp, 40.dp)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = user.name + " " + user.surname, style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = user.username, style = MaterialTheme.typography.subtitle1)
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