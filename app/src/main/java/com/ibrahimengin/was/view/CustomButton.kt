package com.ibrahimengin.was.view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.WASTheme

@Composable
fun ButtonTrailingIcon(buttonClickFunction: () -> Unit, buttonText: String) {
    Button(onClick = buttonClickFunction){
        Text(buttonText)

    }
}

@Composable
fun ButtonLeadingIcon(buttonClickFunction: () -> Unit, buttonText: String){
    Button(onClick = buttonClickFunction){
        Text(buttonText)

    }
}

@Preview
@Composable
fun PreviewButtonDefine(){
    WASTheme {
        ButtonTrailingIcon({},"")
    }
}