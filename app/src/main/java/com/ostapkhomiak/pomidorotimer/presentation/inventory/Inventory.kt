package com.ostapkhomiak.pomidorotimer.presentation.inventory

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate


// visual appearance of Inventory

@SuppressLint("NewApi")
@Composable
fun ShowInventory() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Inventory Test")
        TomatoCard(Tomato(
            id = 1,
            date = LocalDate.now(),
            timeElapsed = "10:00"
        ))

    }

}
