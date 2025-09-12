package com.ostapkhomiak.pomidorotimer.data.inventory

import com.ostapkhomiak.pomidorotimer.R

data class TomatoModel(
    val id: Int,
    val rarity: String,
    val icon: Int = when (rarity) {
        "Legendary" -> {
            R.drawable.legendary_tomato
        }
        "Epic" -> {
            R.drawable.epic_tomato
        }
        "Rare" -> {
            R.drawable.rare_tomato
        }
        else -> {
            R.drawable.common_tomato
        }
    },
    val date: String,
    val timeElapsed: String  // Time
)

