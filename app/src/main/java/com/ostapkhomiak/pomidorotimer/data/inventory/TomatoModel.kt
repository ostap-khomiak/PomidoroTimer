package com.ostapkhomiak.pomidorotimer.data.inventory

import com.ostapkhomiak.pomidorotimer.R
import java.time.LocalDate

data class TomatoModel(
    val id: Int,
    val rarity: String,
    val icon: Int = when (rarity) {
        "Legendary" -> {
            R.drawable.tomato //TODO: replace with different icons
        }
        "Epic" -> {
            R.drawable.tomato
        }
        "Rare" -> {
            R.drawable.tomato
        }
        else -> {
            R.drawable.tomato
        }
    },
    val date: LocalDate,
    val timeElapsed: String  // Time
)

