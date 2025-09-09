package com.ostapkhomiak.pomidorotimer.presentation.timer


import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ostapkhomiak.pomidorotimer.domain.TimerModel
import com.ostapkhomiak.pomidorotimer.ui.theme.Purple40
import java.util.Locale


@Composable
fun ShowTimer(viewModel: TimerModel = viewModel()) {
    var inputText by remember { mutableStateOf("1") }


    val isRunning by viewModel.isRunning.collectAsState()
    val timeElapsed by viewModel.timeElapsed.collectAsState()
    val timeLimit by viewModel.timeLimit.collectAsState()
    val isPaused by viewModel.isPaused.collectAsState()


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

        if (!isRunning && !isPaused) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        inputText = newValue
                    }
                },
                label = { Text("Time in minutes: ") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    val minutes = inputText.toIntOrNull() ?: 1
                    viewModel.startTimer(minutes)
                },
                enabled = inputText.isNotEmpty()
            ) {
                Text("Start Timer")
            }


        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        viewModel.stopTimer()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Stop")
                }



                if( isPaused){
                    Button(
                        onClick = {
                            viewModel.resumeTimer()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Resume")
                    }
                } else{
                    Button(
                        onClick = {
                            viewModel.pauseTimer()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Pause")
                    }
                }

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


    val curPercentage = animateFloatAsState(
        targetValue = if (timeLimitInSeconds > 0)
            timeElapsed.toFloat() / timeLimitInSeconds
        else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = "progressAnimation"
    )

    val timeLeft = timeLimitInSeconds - timeElapsed



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
            text = String.format(Locale.ENGLISH, "%02d:%02d", timeLeft / 60, timeLeft % 60),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}
