package com.ostapkhomiak.pomidorotimer.presentation.inventory

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ostapkhomiak.pomidorotimer.data.inventory.TomatoModel
import com.ostapkhomiak.pomidorotimer.ui.theme.Purple40
import com.ostapkhomiak.pomidorotimer.ui.theme.Purple80

// card visual
@Composable
fun TomatoCard(
    tomato: TomatoModel,
    onDelete: (TomatoModel) -> Unit
) {
    var showDelete by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)) // round corners
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Purple80.copy(0.5f), Purple40.copy(0.5f)), // example purple gradient
                    start = Offset(0f, 0f),
                    end = Offset(0f, 200f)
                )
            )
            .clickable { showDelete = !showDelete }
            .animateContentSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (tomato.rarity) {
                "Legendary" -> {
                    Text(text = tomato.rarity, color = Color(0xFFFFD700), fontWeight = FontWeight.Bold)
                }

                "Epic" -> {
                    Text(text = tomato.rarity, color = Color(0xFF9B30FF), fontWeight = FontWeight.Bold)
                }

                "Rare" -> {
                    Text(text = tomato.rarity, color = Color(0xFF1E90FF), fontWeight = FontWeight.Bold)
                }

                else -> {
                    Text(text = tomato.rarity, color = Color(0xFF888888), fontWeight = FontWeight.Bold)
                }
            }

            Image(
                painter = painterResource(id = tomato.icon),
                contentDescription = "Tomato",
                modifier = Modifier.size(128.dp)
            )

            Text(tomato.date)

            Text(tomato.timeElapsed)


            if (showDelete) {
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onDelete(tomato) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Row {
                        Icon(Icons.Filled.Delete, contentDescription = "delete")
                    }
                }
            }
        }
    }
}

