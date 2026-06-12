package com.example.sticktogether.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sticktogether.Features.auth.login.LoginScreen
import com.example.sticktogether.Features.auth.login.LoginViewModel
import com.example.sticktogether.Features.auth.register.RegisterScreen
import com.example.sticktogether.Features.auth.register.RegisterViewModel
import com.example.sticktogether.Features.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun NavRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login,
        modifier = modifier
    ) {
        composable<Screen.Login> {

            val viewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                viewModel = viewModel,
                onNavigate = { nextScreen -> navController.navigate(nextScreen) })
        }

        composable<Screen.Register> {

            val viewModel: RegisterViewModel = hiltViewModel()

            RegisterScreen(
                viewModel = viewModel,
                onNavigate = { nextScreen -> navController.navigate(nextScreen) })
        }

        composable<Screen.Home> {


            HomeScreen()
        }
    }
}

