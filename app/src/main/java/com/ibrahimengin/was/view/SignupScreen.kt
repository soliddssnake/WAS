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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R

@Composable
fun SignupScreen(navController: NavController, db: FirebaseFirestore) {
    val logo = if (isSystemInDarkTheme()) R.drawable.was_logo_dark else R.drawable.was_logo_light
    val fullName = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val context = LocalContext.current
    val isErrorStateFullName = remember { mutableStateOf(true) }
    val isErrorStateUsername = remember { mutableStateOf(true) }
    val isErrorStateEmail = remember { mutableStateOf(true) }
    val isErrorStatePassword = remember { mutableStateOf(true) }
    val isEnabled = remember { mutableStateOf(true) }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomImage(logo, "WAS Logo", 180.dp, 180.dp)
            CustomOutlinedTextField(
                fullName.value,
                {
                    fullName.value = it
                    isErrorStateFullName.value = when {
                        it.length < 3 -> true
                        else -> false
                    }
                },
                stringResource(R.string.fullName),
                isErrorStateFullName.value,
                keyboardOption = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
            )
            CustomOutlinedTextField(
                username.value, {
                    username.value = it
                    isErrorStateUsername.value = when {
                        it.length < 3 -> true
                        else -> false
                    }
                }, stringResource(R.string.username), isErrorStateUsername.value
            )
            CustomOutlinedTextField(
                email.value,
                {
                    email.value = it
                    isErrorStateEmail.value = when {
                        it.length < 5 -> true
                        !email.value.matches(PatternsCompat.EMAIL_ADDRESS.toRegex()) -> true
                        else -> false
                    }
                },
                stringResource(R.string.emailAddress),
                isErrorStateEmail.value,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            PasswordField(
                password.value, {
                    password.value = it
                    isErrorStatePassword.value = when {
                        it.length < 6 -> true
                        else -> false
                    }
                }, stringResource(R.string.password), isErrorStatePassword.value
            )
            DatePickField(stringResource(R.string.birthday), Icons.Filled.Cake, stringResource(R.string.birthday), date)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonLeadingIcon(
                    { navController.popBackStack() },
                    stringResource(R.string.goBack),
                    Icons.Filled.ArrowBack,
                    stringResource(R.string.goBack),
                    colorInput = Color.Gray
                )
                ButtonTrailingIcon(
                    {
                        db.collection("users").document(email.value).get().addOnSuccessListener { document ->
                            if (document.exists()) {
                                Toast.makeText(context, "bundan var gardaaaş", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(context, "olur olur yeriz", Toast.LENGTH_LONG).show()
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                    },/*TODO Questions ekranına yönlenecek düzenlenecek ve email firebase bakılacak
                        Butona tıklandığında isEnabled false olacak eğer email hatası alırsa tekrar true
                    */
                    stringResource(R.string.next),
                    Icons.Filled.ArrowForward,
                    stringResource(R.string.next),
                    enableInput = !isErrorStateFullName.value && !isErrorStateEmail.value && !isErrorStatePassword.value && !isErrorStateUsername.value && date.value.isNotEmpty() && isEnabled.value
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignupScreen() {
    WASTheme {
        SignupScreen(navController = rememberNavController(), Firebase.firestore)
    }
}