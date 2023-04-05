package com.example.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeui.Utils.Constants


import kotlinx.coroutines.launch

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMainScreen()


//    val navController = rememberNavController()
////
//            Surface(color = Color.White) {
////                // Scaffold Component
//                Scaffold(
////                    // Bottom navigation
//                    bottomBar = {
//                        BottomNavigationBar(navController = navController)
//                    }, content = { padding ->
//                        // Navhost: where screens are placed
//                        NavHostContainer(navController = navController, padding = padding)
//                    }
//                )
//            }
        }
    }
}

@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = DrawerScreens.Home.route
            ) {
                composable(DrawerScreens.Home.route) {
                    Home(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Account.route) {
                    Account(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Help.route) {
                    Help(
                        navController = navController
                    )
                }
            }
        }


    }

}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",

        // Set the padding provided by scaffold
//        modifier = Modifier.padding(paddingValues = padding),

        builder = {

//             route : Home
            composable("home") {
                HomeScreen()
            }

            // route : search
            composable("search") {
                SearchScreen()
            }

//             route : profile
            composable("profile") {
                ProfileScreen()
            }
        })

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = Color(0xFF0F9D58)
    ) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello 2nd activity $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    ComposeuiTheme {
//        Greeting("Android")
//    }
//}
@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    AppMainScreen()
}