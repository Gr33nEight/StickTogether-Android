package com.example.sticktogether.Features.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sticktogether.Features.auth.login.view.LoginFooter
import com.example.sticktogether.Features.auth.login.view.LoginForm
import com.example.sticktogether.Features.auth.login.view.LoginHeader
import com.example.sticktogether.Features.auth.login.view.LoginSocials
import com.example.sticktogether.Navigation.Screen
import com.example.sticktogether.Resources.Components.Colors

@Composable

fun LoginScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: LoginViewModel
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = state.isLoginSuccessful) {
        if (state.isLoginSuccessful) {
            onNavigate(Screen.Home)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor)
            .padding(top = 49.dp, start = 3.dp, end = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 14.dp, end = 17.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoginHeader()

            Spacer(modifier = Modifier.height(40.dp))

            LoginForm(
                email = state.emailorusername,
                password = state.password,
                passwordError = state.passwordError,
                showError = state.showErrors,
                onEmailChange = { viewModel.onEmailChange(it) },
                onPasswordChange = { viewModel.onPasswordChange(it) },
                onLoginClick = { viewModel.LoginSignIn() }
            )

            Spacer(modifier = Modifier.height(40.dp))

            LoginSocials()

            Spacer(modifier = Modifier.height(100.dp))

            LoginFooter(
                onRegisterClick = { onNavigate(Screen.Register) }
            )
        }
    }
}

