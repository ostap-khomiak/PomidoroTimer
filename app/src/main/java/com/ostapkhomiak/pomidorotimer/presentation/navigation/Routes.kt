package com.ostapkhomiak.pomidorotimer.presentation.navigation



sealed class Screen(val route: String) {
    object Timer : Screen("timer")
    object Inventory : Screen("inventory")
    object About : Screen("about")


}