package com.example.sticktogether.App

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sticktogether.Features.auth.splash.SplashView
import com.example.sticktogether.Features.auth.splash.SplashViewModel
import com.example.sticktogether.Navigation.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            false
        }
        setContent {

            val showCustomSplash by viewModel.showCustomSplash.collectAsState()

            if (showCustomSplash) {
                SplashView()
            } else {
                MainScreen()
            }
        }
    }
}
