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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder

@Composable
fun SignupScreen(navController: NavController) {
    val logo = if (isSystemInDarkTheme()) R.drawable.was_logo_dark else R.drawable.was_logo_light
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val gender = listOf(stringResource(R.string.male), stringResource(R.string.female))
    val genderExpandState = remember { mutableStateOf(false) }
    val genderSelected = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val isErrorStateName = remember { mutableStateOf(true) }
    val isErrorStateSurname = remember { mutableStateOf(true) }
    val isErrorStateUsername = remember { mutableStateOf(true) }
    val isErrorStatePhoneNumber = remember { mutableStateOf(true) }
    val isErrorStateEmail = remember { mutableStateOf(true) }
    val isErrorStatePassword = remember { mutableStateOf(true) }
    val nextButtonIsEnabled = remember { mutableStateOf(true) }
    val context = LocalContext.current
    val emailValidationMessage = stringResource(R.string.thisEmailIsAlreadyInUse)
    val usernameValidationMessage = stringResource(R.string.thisUsernameIsAlreadyInUse)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomImage(logo, "WAS Logo", 120.dp, 120.dp)

            CustomOutlinedTextField(
                name.value,
                {
                    name.value = it
                    isErrorStateName.value = when {
                        it.length < 3 -> true
                        else -> false
                    }
                },
                stringResource(R.string.name),
                isErrorStateName.value,
                keyboardOption = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
            )
            CustomOutlinedTextField(
                surname.value,
                {
                    surname.value = it
                    isErrorStateSurname.value = when {
                        it.length < 3 -> true
                        else -> false
                    }
                },
                stringResource(R.string.surname),
                isErrorStateSurname.value,
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
                phoneNumber.value,
                {
                    if (it.length < 11) phoneNumber.value = it
                    isErrorStatePhoneNumber.value = when (it.length) {
                        10 -> false
                        else -> true
                    }
                },
                stringResource(R.string.phoneNumber),
                isErrorStatePhoneNumber.value,
                KeyboardOptions(keyboardType = KeyboardType.Phone)
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

            Dropdown(
                genderSelected,
                { genderSelected.value = it },
                genderExpandState,
                gender,
                stringResource(R.string.gender)
            )

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
                        nextButtonIsEnabled.value = false

                        Firebase.firestore.collection("users").document(email.value).get().addOnSuccessListener {
                            if (it.exists()) {
                                Toast.makeText(context, emailValidationMessage, Toast.LENGTH_LONG).show()
                            } else {
                                Firebase.firestore.collection("users").whereEqualTo("username", username.value).get()
                                    .addOnSuccessListener {
                                        if (it.documents.isEmpty()) {
                                            navController.navigate(ScreenHolder.QuestionsScreen.toString())
                                        } else {
                                            Toast.makeText(context, usernameValidationMessage, Toast.LENGTH_LONG).show()
                                        }
                                    }
                            }

                        }.addOnFailureListener {

                        }
                        nextButtonIsEnabled.value = true


                    },
                    stringResource(R.string.next),
                    Icons.Filled.ArrowForward,
                    stringResource(R.string.next),
                    enableInput = (!isErrorStateName.value && !isErrorStateEmail.value && !isErrorStatePassword.value && !isErrorStateUsername.value && !isErrorStatePhoneNumber.value && date.value.isNotEmpty() && genderSelected.value.isNotEmpty() && nextButtonIsEnabled.value)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignupScreen() {
    WASTheme {
        SignupScreen(rememberNavController())
    }
}