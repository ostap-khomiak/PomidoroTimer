package com.ostapkhomiak.pomidorotimer.presentation.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ostapkhomiak.pomidorotimer.R
import com.ostapkhomiak.pomidorotimer.ui.theme.PurpleGrey40
import java.time.LocalDate

// ui model


data class Tomato(
    val id: Int,
    val icon: Int = R.drawable.tomato,
    val date: LocalDate,
    val timeElapsed: String  // Time
)



// card visual
@Composable
fun TomatoCard(tomato: Tomato) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp)) // round corners
        .background(color = PurpleGrey40)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Tomato: ${tomato.id}")

            Image(
                painter = painterResource(id = tomato.icon),
                contentDescription = "Tomato"
            )

            Text(tomato.date.toString())

            Text(tomato.timeElapsed)

        }
    }
}

