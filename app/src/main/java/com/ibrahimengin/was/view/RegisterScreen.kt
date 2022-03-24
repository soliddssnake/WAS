package com.ibrahimengin.was.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.WASTheme
import com.ibrahimengin.was.R

@Composable
fun RegisterScreen(){
    val logo = if (isSystemInDarkTheme()) R.drawable.was_logo_dark  else R.drawable.was_logo_light
    val fullName = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 5.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            CustomImage(logo, "WAS Logo", 180.dp,180.dp)
            CustomOutlinedTextField(fullName.value, {fullName.value = it}, stringResource(R.string.fullName))
            CustomOutlinedTextField(username.value, {username.value = it}, stringResource(R.string.username))
            CustomOutlinedTextField(email.value, {email.value = it}, stringResource(R.string.emailAddress))
            PasswordField(password.value, {password.value = it}, stringResource(R.string.password))

        }
    }
}

@Preview
@Composable
fun PreviewRegisterScreen(){
    WASTheme {
        RegisterScreen()
    }
}