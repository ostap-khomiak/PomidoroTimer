package com.ostapkhomiak.pomidorotimer.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem (
    val title: String,
    val route: String,
    val iconUnselected: ImageVector,
    val iconSelected: ImageVector,
    val hasNews: Boolean
)