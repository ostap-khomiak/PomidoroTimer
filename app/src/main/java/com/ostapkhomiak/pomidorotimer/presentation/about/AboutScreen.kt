package com.ostapkhomiak.pomidorotimer.presentation.about


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ShowAbout() {

    val chances =  listOf(
        "Legendary" to 1, // create pairs
        "Epic" to 15,
        "Rare" to 30,
        "Common" to 54
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn{
            items(chances) { (rarity, chance) ->
                ChancePanel(rarity, chance)
            }
        }
    }
}
