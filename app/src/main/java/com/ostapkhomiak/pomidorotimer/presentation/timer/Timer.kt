package com.ostapkhomiak.pomidorotimer.presentation.timer


import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ostapkhomiak.pomidorotimer.ui.theme.Purple40
import kotlinx.coroutines.delay


@Composable
fun ShowTimer() {
    var inputText by remember { mutableStateOf("1") }
    var selectedMinutes by remember { mutableStateOf(1) }
    var isRunning by remember { mutableStateOf(false) }
    var timeElapsed by remember { mutableStateOf(0) }
    var timeLimit by remember { mutableStateOf(60) }

    // Timer countdown
    LaunchedEffect(isRunning) {
        if (isRunning) {
            timeElapsed = 0
            while (timeElapsed < timeLimit && isRunning) {
                delay(1000L)
                timeElapsed++
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressBar(
            timeElapsed = timeElapsed,
            timeLimitInSeconds = timeLimit
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (!isRunning) {

            OutlinedTextField(
                value = inputText,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        inputText = newValue
                    }
                },
                label = { Text("Timer for: ") },
                singleLine = true
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    selectedMinutes = inputText.toInt()
                    timeLimit = (selectedMinutes * 60)
                    timeElapsed = 0
                    isRunning = true
                },
                enabled = inputText.isNotEmpty()
            ) {
                Text("Start Timer")
            }
        } else {
            Button(
                onClick = {
                    isRunning = false
                }
            ) {
                Text("Stop Timer")
            }
        }

    }
}


// Progress Bar for Timer
@Composable
fun CircularProgressBar(
    timeElapsed: Int,
    timeLimitInSeconds: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 100.dp,
    color: Color = Purple40,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) (timeElapsed.toFloat() / timeLimitInSeconds) else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )

    )

    val timeLeft = timeLimitInSeconds - timeElapsed

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            // Text formatting MM:SS
            text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}
