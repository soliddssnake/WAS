package com.ibrahimengin.was.view

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Cake
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.PatternsCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.ibrahimengin.was.R

@Composable
fun SignupScreen(navController: NavController){
    val logo = if (isSystemInDarkTheme()) R.drawable.was_logo_dark  else R.drawable.was_logo_light
    val fullName = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 5.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            CustomImage(logo, "WAS Logo", 180.dp,180.dp)
            CustomOutlinedTextField(fullName.value,
                {
                    fullName.value = it
                },
                stringResource(R.string.fullName),
                keyboardOption =  KeyboardOptions(capitalization = KeyboardCapitalization.Words))
            CustomOutlinedTextField(username.value, {username.value = it}, stringResource(R.string.username))
            CustomOutlinedTextField(email.value, {email.value = it},
                stringResource(R.string.emailAddress),
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Email))
            PasswordField(password.value, {password.value = it}, stringResource(R.string.password))
            DatePickField(stringResource(R.string.birthday),Icons.Filled.Cake, stringResource(R.string.birthday))
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                ButtonLeadingIcon({navController.popBackStack()},
                    stringResource(R.string.goBack), Icons.Filled.ArrowBack, stringResource(R.string.goBack),
                    colorInput = Color.Gray)
                ButtonTrailingIcon( {if (email.value.matches(PatternsCompat.EMAIL_ADDRESS.toRegex())
                    || fullName.value.isNotEmpty() || username.value.isNotEmpty() )
                    Toast.makeText(context,"Doldurduğundan emin ol!",Toast.LENGTH_LONG).show() },//TODO Questions ekranına yönlenecek ve düzenlenecek
                    stringResource(R.string.next),Icons.Filled.ArrowForward, stringResource(R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignupScreen(){
    WASTheme {
        SignupScreen(navController = rememberNavController())
    }
}