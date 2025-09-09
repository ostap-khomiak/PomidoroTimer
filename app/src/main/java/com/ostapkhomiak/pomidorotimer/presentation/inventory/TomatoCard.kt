package com.ostapkhomiak.pomidorotimer.presentation.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.ostapkhomiak.pomidorotimer.data.inventory.TomatoModel
import com.ostapkhomiak.pomidorotimer.ui.theme.PurpleGrey40


// card visual
@Composable
fun TomatoCard(tomato: TomatoModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)) // round corners
            .background(
                color = PurpleGrey40.copy(alpha = 0.2f) // transparency
            )
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

