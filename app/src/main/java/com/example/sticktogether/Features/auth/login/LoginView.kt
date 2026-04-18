package com.example.sticktogether.Features.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.Navigation.Screen
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors
import com.example.sticktogether.Resources.Components.CustomPasswordField
import com.example.sticktogether.Resources.Components.CustomTextField

@Composable

fun LoginView(
    onNavigate: (Screen) -> Unit,
    viewModel: LoginViewModel
) {

    val interactionSource = remember { MutableInteractionSource() }

    val state by viewModel.uiState.collectAsState()

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 57.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Welcome Back",
                    color = Colors.HeaderColor,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )
                Text(
                    text = "Login to your account with Email and Password",
                    color = Colors.HeaderColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                thickness = 0.5.dp,
                color = Colors.HorizontalDivider
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                value = state.emailorusername,
                onValueChange = { newValue -> viewModel.onEmailChange(newValue)},
                placeholder = "Email",
                leadingIcon = R.drawable.ic_mail
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomPasswordField(
                value = state.password,
                onValueChange = { newValue -> viewModel.onPasswordChange(newValue)},
                placeholder = "Password",
                leadingIcon = R.drawable.ic_lock,
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "forgot password?",
                color = Colors.Secondary,
                textAlign = TextAlign.Start,
                fontSize = 13.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .width(338.dp),
                fontWeight = FontWeight.W300,
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { viewModel.LoginSignIn()},
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Colors.Primary),

            ) {
                Text(text = "Login", color = Color.White)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 0.5.dp,
                    color = Colors.HorizontalDivider
                )

                Text(
                    text = "Or",
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    color = Colors.HorizontalDivider,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W300
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 0.5.dp,
                    color = Colors.HorizontalDivider
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(R.drawable.ic_social_medias),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.height(100.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Don't have account?",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W500,
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "Create New",
                    color = Colors.Secondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W300,
                    textDecoration = TextDecoration.Underline,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .clickable { onNavigate(Screen.Register) }
                )
            }
        }
    }
}
