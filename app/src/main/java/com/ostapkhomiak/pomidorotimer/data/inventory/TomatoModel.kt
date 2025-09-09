package com.ostapkhomiak.pomidorotimer.data.inventory

import com.ostapkhomiak.pomidorotimer.R
import java.time.LocalDate

data class TomatoModel(
    val id: Int,
    val icon: Int = R.drawable.tomato,
    val date: LocalDate,
    val timeElapsed: String  // Time
)

