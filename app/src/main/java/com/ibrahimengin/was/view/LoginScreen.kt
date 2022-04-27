package com.ibrahimengin.was.view

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.PatternsCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder

@Composable
fun LoginScreen(navController: NavController) {
    val logo = if (isSystemInDarkTheme()) R.drawable.was_logo_dark else R.drawable.was_logo_light
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current
    val warningMessage = stringResource(R.string.emailOrPasswordIsIncorrect)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomImage(logo, "WAS Logo", 250.dp, 250.dp)
            CustomOutlinedTextField(email.value, { email.value = it }, stringResource(R.string.emailAddress))
            PasswordField(password.value, { password.value = it }, stringResource(R.string.password))
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(stringResource(R.string.forgotPassword))//TODO clickable-onClick fonksionu eklenecek
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                ButtonTrailingIcon(
                    { navController.navigate(ScreenHolder.SignupScreen.toString()) },
                    stringResource(R.string.signup),
                    Icons.Filled.PersonAddAlt1,
                    stringResource(R.string.signup),
                    Color.Gray
                )
                ButtonTrailingIcon(
                    {
                        val auth = Firebase.auth
                        if (email.value.isEmpty() || password.value.isEmpty() || !email.value.matches(PatternsCompat.EMAIL_ADDRESS.toRegex()) || password.value.length < 6) {
                            Toast.makeText(context, warningMessage, Toast.LENGTH_LONG).show()
                        } else {
                            auth.signInWithEmailAndPassword(email.value, password.value).addOnSuccessListener {
                                navController.navigate(ScreenHolder.MainScreen.toString()) {
                                    popUpTo(ScreenHolder.MainScreen.toString()) {
                                        inclusive = true
                                    }
                                }
                            }.addOnFailureListener {
                                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                                //TODO string resource olması lazım
                            }
                        }
                    }, stringResource(R.string.login), Icons.Filled.Login, stringResource(R.string.login)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    WASTheme {
        LoginScreen(navController = rememberNavController())
    }
}