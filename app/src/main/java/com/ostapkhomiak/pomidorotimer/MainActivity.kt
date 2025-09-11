package com.ostapkhomiak.pomidorotimer


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ostapkhomiak.pomidorotimer.presentation.navigation.NavigationInit
import com.ostapkhomiak.pomidorotimer.ui.theme.PomidoroTimerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

