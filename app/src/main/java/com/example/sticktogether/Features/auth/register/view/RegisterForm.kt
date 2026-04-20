package com.example.sticktogether.Features.auth.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomButtonField
import com.example.sticktogether.Resources.Components.CustomPasswordField
import com.example.sticktogether.Resources.Components.CustomTextField
import com.example.sticktogether.Resources.Enums.PasswordError
import com.example.sticktogether.Resources.Enums.asString

@Composable

fun RegisterForm(
    email: String,
    password: String,
    nickname: String,
    passwordError: PasswordError,
    confirmPasswordError: PasswordError,
    confirmPassword: String,
    showError: Boolean,
    onRegisterClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        CustomTextField(
            value = nickname,
            onValueChange = onNicknameChange,
            placeholder = "Nickname",
            leadingIcon = R.drawable.ic_person
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = "Email",
            leadingIcon = R.drawable.ic_mail
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomPasswordField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "New Password",
            leadingIcon = R.drawable.ic_lock,
            isPassword = true,
            isError = showError && passwordError!= PasswordError.NONE
        )

        if (showError && passwordError != PasswordError.NONE) {
            Text(
                text = passwordError.asString(),
                color = Colors.Error,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                fontWeight = FontWeight.W400
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        CustomPasswordField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = "Confirm Password",
            leadingIcon = R.drawable.ic_lock,
            isPassword = true,
            isError = showError && confirmPasswordError != PasswordError.NONE
        )

        if (showError && confirmPasswordError != PasswordError.NONE) {
            Text(
                text = confirmPasswordError.asString(),
                color = Colors.Error,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp),
                fontWeight = FontWeight.W400
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        CustomButtonField(
            text = "Create Account",
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}