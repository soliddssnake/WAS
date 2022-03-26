package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Cake
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.WASTheme

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