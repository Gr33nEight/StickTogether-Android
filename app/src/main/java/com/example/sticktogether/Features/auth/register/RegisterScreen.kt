package com.example.sticktogether.Features.auth.register


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sticktogether.Features.auth.register.view.RegisterFooter
import com.example.sticktogether.Features.auth.register.view.RegisterForm
import com.example.sticktogether.Features.auth.register.view.RegisterHeader
import com.example.sticktogether.Features.auth.register.view.RegisterSocials
import com.example.sticktogether.Navigation.Screen
import com.example.sticktogether.Resources.Components.Colors

@Composable
fun RegisterScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: RegisterViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor)
            .padding(top = 30.dp, start = 3.dp, end = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 14.dp, end = 17.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RegisterHeader()

            Spacer(modifier = Modifier.height(30.dp))

            RegisterForm(
                email = state.emailorusername,
                password = state.password,
                nickname = state.nickname,
                passwordError = state.passwordError,
                confirmPasswordError = state.confirmPasswordError,
                confirmPassword = state.confirmPassword,
                showError = state.showErrors,
                onRegisterClick = { viewModel.RegisterSignIn()},
                onEmailChange = { viewModel.onEmailChange(it)},
                onPasswordChange = { viewModel.onPasswordChange(it)},
                onConfirmPasswordChange = { viewModel.onConfirmPasswordChange(it)},
                onNicknameChange = { viewModel.onNicknameChange(it)}
            )

            Spacer(modifier = Modifier.height(40.dp))

            RegisterSocials()

            Spacer(modifier = Modifier.height(40.dp))

            RegisterFooter(
                onLoginClick = { onNavigate(Screen.Login) }
            )

        }
    }
}