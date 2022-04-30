package com.ibrahimengin.was.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToHomeScreen
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.SetupNavGraph

@Composable
fun MainScreen() {
    val currentUser = Firebase.auth.currentUser
    val navController: NavHostController = rememberNavController()
    if (currentUser != null) {
        Scaffold(bottomBar = {
            BottomBar(navController = navController)
        }) {
            SetupNavGraph(navController = navController)
        }
    } else {
        Scaffold {
            SetupNavGraph(navController = navController)
        }
    }


}

@Composable
fun HomeScreen() {/*topBar = {
        MainScreenTopBar()
    },*/
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Heloo Home")
        }
    }
}

@Composable
fun HomeScreenTopBar() {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.h3)
        }, actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.AddToHomeScreen, contentDescription = ""
                )
            }

            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Filled.Notifications, contentDescription = ""
                )
            }
        }
    )
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
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
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

        //MainScreenTopBar()
    }
}