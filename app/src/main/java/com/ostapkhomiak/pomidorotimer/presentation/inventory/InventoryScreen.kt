package com.ostapkhomiak.pomidorotimer.presentation.inventory


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ostapkhomiak.pomidorotimer.domain.InventoryViewModel


// visual appearance of Inventory

@Composable
fun ShowInventory(viewModel: InventoryViewModel ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        val tomatoes by viewModel.inventoryList.collectAsState()
        println(tomatoes.toString())
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(tomatoes) { tomato ->
                TomatoCard(tomato)
            }
        }
    }
}
