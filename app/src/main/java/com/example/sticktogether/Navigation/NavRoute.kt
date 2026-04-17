package com.example.sticktogether.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sticktogether.Features.auth.login.LoginView
import com.example.sticktogether.Features.auth.login.LoginViewModel
import com.example.sticktogether.Features.auth.register.RegisterView
import com.example.sticktogether.Features.auth.register.RegisterViewModel

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

            val viewModel: LoginViewModel = viewModel()

            LoginView(
                viewModel = viewModel,
                onNavigate = { nextScreen -> navController.navigate(nextScreen) })
        }

        composable<Screen.Register> {

            val viewModel: RegisterViewModel = viewModel()

            RegisterView(
                viewModel = viewModel,
                onNavigate = { nextScreen -> navController.navigate(nextScreen) })
        }
    }
}

