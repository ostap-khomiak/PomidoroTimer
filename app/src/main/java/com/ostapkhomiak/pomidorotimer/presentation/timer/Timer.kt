package com.ostapkhomiak.pomidorotimer.presentation.timer


import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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


@Composable
fun ShowTimer(){
    Box (
        modifier = Modifier.fillMaxWidth().padding(128.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressBar(200, 500)
    }
}


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
    var animationPlayed by remember { mutableStateOf(false)}

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) (timeElapsed.toFloat() / timeLimitInSeconds) else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )

    )

    var timeLeft = timeLimitInSeconds - timeElapsed

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
            text = timeLeft.toString(),  // TODO: style it to time format MM:SS
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}
