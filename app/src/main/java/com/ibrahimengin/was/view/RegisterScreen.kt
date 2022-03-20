package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.WASTheme

@Composable
fun RegisterScreen(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column() {
            Text("Hellloooooo")
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