package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.WASTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder

@Composable
fun ButtonTrailingIcon(
    buttonClickFunction: () -> Unit,
    buttonText: String,
    iconsInputs: ImageVector,
    iconsContentDescription: String,
    colorInput: Color? = MaterialTheme.colors.primary,
    enableInput: Boolean? = true
) {
    Button(onClick = buttonClickFunction, colors = ButtonDefaults.buttonColors(colorInput!!), enabled = enableInput!!) {
        Text(buttonText)
        Spacer(modifier = Modifier.width(2.dp))
        Icon(imageVector = iconsInputs, iconsContentDescription, modifier = Modifier.size(18.dp))
    }
}

@Composable
fun ButtonLeadingIcon(
    buttonClickFunction: () -> Unit,
    buttonText: String,
    iconsInputs: ImageVector,
    iconsContentDescription: String,
    colorInput: Color? = MaterialTheme.colors.primary
) {
    Button(onClick = buttonClickFunction, colors = ButtonDefaults.buttonColors(colorInput!!)) {
        Icon(imageVector = iconsInputs, iconsContentDescription, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(2.dp))
        Text(buttonText)
    }
}

@Composable
fun MoreVertButton(
    navController: NavController
) {

    val expanded = remember { mutableStateOf(false) }
    val auth = Firebase.auth
    Column {
        IconButton(onClick = { expanded.value = true }) {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = stringResource(R.string.more))
        }
        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                Text(stringResource(R.string.editProfile))
            }
            DropdownMenuItem(onClick = {
                auth.signOut()
                navController.navigate(ScreenHolder.LoginScreen.route) {
                    popUpTo(ScreenHolder.LoginScreen.route) {
                        inclusive = true
                    }
                }

            }) {
                Text(stringResource(R.string.logout))
            }
        }
    }
}

@Preview
@Composable
fun PreviewButtonDefine() {
    WASTheme {
        Column {
            ButtonTrailingIcon({}, "BUTTON", Icons.Filled.Alarm, "Button")
            ButtonLeadingIcon({}, "LBUTTON", Icons.Filled.Cake, "")
        }

    }
}