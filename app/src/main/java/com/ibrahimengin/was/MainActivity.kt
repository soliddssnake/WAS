package com.ibrahimengin.was

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.example.compose.WASTheme
import com.ibrahimengin.was.view.MainScreen

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WASTheme {
                MainScreen()
            }
        }
    }
}