package com.ostapkhomiak.pomidorotimer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ostapkhomiak.pomidorotimer.presentation.navigation.NavigationInit
import com.ostapkhomiak.pomidorotimer.ui.theme.PomidoroTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PomidoroTimerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationInit()
                }
            }
        }
    }
}

