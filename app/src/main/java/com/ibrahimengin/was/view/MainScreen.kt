package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.SetupNavGraph

@Composable
fun MainScreen() {
    val currentUser = Firebase.auth.currentUser
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    if (currentUser != null) {
        Scaffold(bottomBar = {
            when (navBackStackEntry?.destination?.route) {
                "add_post_screen" -> {}
                "login_screen" -> {}
                else -> {
                    BottomBar(navController = navController)
                }
            }

        }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) { SetupNavGraph(navController = navController) }

        }
    } else {
        Scaffold {
            SetupNavGraph(navController = navController)
        }
    }


}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        ScreenHolder.HomeScreen,
        ScreenHolder.SearchScreen,
        ScreenHolder.ProfileScreen,
        ScreenHolder.MessagesScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItemtoBottomBar(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItemtoBottomBar(
    screen: ScreenHolder,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(imageVector = screen.icon!!, contentDescription = "Navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Preview
@Composable
fun PreviewMainScreen() {
    WASTheme {
    }
}