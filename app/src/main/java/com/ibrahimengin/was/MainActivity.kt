package com.ibrahimengin.was

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.ibrahimengin.was.view.LoginScreen

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WASTheme {
                navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}