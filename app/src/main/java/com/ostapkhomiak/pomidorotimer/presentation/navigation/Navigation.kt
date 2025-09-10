package com.ostapkhomiak.pomidorotimer.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ostapkhomiak.pomidorotimer.domain.InventoryViewModel
import com.ostapkhomiak.pomidorotimer.domain.TimerViewModel
import com.ostapkhomiak.pomidorotimer.domain.TomatoRepository
import com.ostapkhomiak.pomidorotimer.presentation.inventory.ShowInventory
import com.ostapkhomiak.pomidorotimer.presentation.settings.ShowSettings
import com.ostapkhomiak.pomidorotimer.presentation.timer.ShowTimer

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationInit() {
    val context = LocalContext.current
    val repository = remember { TomatoRepository(context.applicationContext) }

    val inventoryViewModel = remember { InventoryViewModel(repository) }
    val timerViewModel = remember { TimerViewModel(repository) }


    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NavigationBarInit(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Timer.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Timer.route) { ShowTimer(viewModel = timerViewModel) }
            composable(Screen.Inventory.route) { ShowInventory(viewModel = inventoryViewModel) }
            composable(Screen.Settings.route) { ShowSettings() }

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
                label = { Text(text = item.title) }
            )
        }
    }

}