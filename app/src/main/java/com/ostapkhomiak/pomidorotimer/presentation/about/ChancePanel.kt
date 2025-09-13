package com.ostapkhomiak.pomidorotimer.presentation.about


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ostapkhomiak.pomidorotimer.R
import com.ostapkhomiak.pomidorotimer.ui.theme.Purple40
import com.ostapkhomiak.pomidorotimer.ui.theme.Purple80

// chances visuals
@Composable
fun ChancePanel(rarity: String, chance: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)) // round corners
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Purple80.copy(0.4f), Purple40.copy(0.4f)),
                    start = Offset(0f, 0f),
                    end = Offset(200f, 400f)
                )
            )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            when (rarity) {
                "Legendary" -> {
                    CreateImage(R.drawable.legendary_tomato, "Legendary")
                    Text(text = rarity, color = Color(0xFFFFD700), fontWeight = FontWeight.Bold)
                }

                "Epic" -> {
                    CreateImage(R.drawable.epic_tomato, "Epic")
                    Text(text = rarity, color = Color(0xFF9B30FF), fontWeight = FontWeight.Bold)
                }

                "Rare" -> {
                    CreateImage(R.drawable.rare_tomato, "Rare")
                    Text(text = rarity, color = Color(0xFF1E90FF), fontWeight = FontWeight.Bold)
                }

                else -> {
                    CreateImage(R.drawable.common_tomato, "Common")
                    Text(
                        text = rarity, color = Color(0xFF888888), fontWeight = FontWeight.Bold
                    )
                }
            }



            Text(
                text = "${chance}%",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(end = 16.dp)
            )
        }

    }
}


// helper function
@Composable
fun CreateImage(id: Int, contentDescription: String){
    Image(
        painterResource(id),
        contentDescription = contentDescription,
        modifier = Modifier.size(96.dp)
    )
}