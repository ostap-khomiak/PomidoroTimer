package com.ostapkhomiak.pomidorotimer.presentation.inventory

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ostapkhomiak.pomidorotimer.data.inventory.TomatoModel
import com.ostapkhomiak.pomidorotimer.ui.theme.PurpleGrey40


// card visual
@Composable
fun TomatoCard(
    tomato: TomatoModel,
    onDelete: (TomatoModel) -> Unit
    ) {
    var showDelete by remember { mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)) // round corners
            .background(
                color = PurpleGrey40.copy(alpha = 0.2f) // transparency
            )
            .clickable{showDelete = !showDelete}
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(tomato.rarity)

            Image(
                painter = painterResource(id = tomato.icon),
                contentDescription = "Tomato"
            )

            Text(tomato.date.toString())

            Text(tomato.timeElapsed)


            if (showDelete) {
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {onDelete(tomato)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Row{
                        Icon(Icons.Filled.Delete, contentDescription = null)

                        Text("Delete")
                    }
                }
            }
        }
    }
}

