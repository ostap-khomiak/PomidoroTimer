package com.ostapkhomiak.pomidorotimer.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationInit() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NavigationBarInit(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "timer",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("timer") { Text("timer") } // TODO: implement screens
            composable("inventory") { Text("inventory") }
            composable("settings") { Text("settings") }
        }
    }
}


@Composable
fun NavigationBarInit(navController: NavController) {
    val items = listOf(
        NavItem(
            "Timer",
            "timer",
            Icons.Outlined.Timer,
            Icons.Filled.Timer,
            false
        ),
        NavItem(
            "Inventory",
            "inventory",
            Icons.Outlined.Home,
            Icons.Filled.Home,
            false
        ),
        NavItem(
            "Settings",
            "settings",
            Icons.Outlined.Settings,
            Icons.Filled.Settings,
            false
        )
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }


    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) item.iconSelected else item.iconUnselected,
                            contentDescription = item.title
                        )
                    }

                },
                label = { Text(text = item.title) })
        }
    }


}