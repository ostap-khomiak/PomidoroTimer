package com.ostapkhomiak.pomidorotimer.presentation.inventory

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ostapkhomiak.pomidorotimer.data.inventory.Tomato
import java.time.LocalDate


// visual appearance of Inventory

@SuppressLint("NewApi")
@Composable
fun ShowInventory() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {

        // for debugging purposes
        val tomato1Test = Tomato(
            id = 1,
            date = LocalDate.now(),
            timeElapsed = "10:00"
        )
        val tomato2Test = Tomato(
            id = 2,
            date = LocalDate.now(),
            timeElapsed = "25:00"
        )

        val tomatos = listOf(tomato1Test, tomato2Test, tomato1Test, tomato2Test, tomato1Test, tomato2Test,tomato1Test)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(tomatos) { tomato ->
                TomatoCard(tomato)
            }
        }


    }

}
