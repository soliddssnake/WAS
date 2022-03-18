package com.ibrahimengin.was

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.WASTheme
import com.ibrahimengin.was.view.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WASTheme {
                LoginScreen()
            }
        }
    }
}