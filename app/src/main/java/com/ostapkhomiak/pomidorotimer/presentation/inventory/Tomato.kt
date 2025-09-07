package com.ostapkhomiak.pomidorotimer.presentation.inventory

import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime

// ui model


data class Tomato (
    val title: String,
    val icon: ImageVector,
    val dateTime: LocalDateTime,
    val timeElapsed: String  // Time
)