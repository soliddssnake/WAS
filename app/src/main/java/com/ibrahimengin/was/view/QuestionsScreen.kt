package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.ibrahimengin.was.viewmodel.UserSharedViewModel

@Composable
fun QuestionsScreen(navController: NavController, userSharedViewModel: UserSharedViewModel) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
                modifier = Modifier.padding(horizontal = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
            Text(userSharedViewModel.user!!.username)
        }
    }
}

@Preview
@Composable
fun PreviewQuestionsScreen() {
    WASTheme {
        QuestionsScreen(rememberNavController(), UserSharedViewModel())
    }
}