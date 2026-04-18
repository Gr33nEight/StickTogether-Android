package com.example.sticktogether.Features.auth.login.view

import android.R.attr.onClick
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomButtonField
import com.example.sticktogether.Resources.Components.CustomPasswordField

import com.example.sticktogether.Resources.Components.CustomTextField

@Composable
fun LoginForm(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
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
            placeholder = "Password",
            leadingIcon = R.drawable.ic_lock,
            isPassword = true
        )

        Text(
            text = "forgot password?",
            color = Colors.Secondary,
            fontSize = 13.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(top = 15.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomButtonField(
            text = "Login",
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}