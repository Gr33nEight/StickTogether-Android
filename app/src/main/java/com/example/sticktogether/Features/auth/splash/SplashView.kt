package com.example.sticktogether.Features.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors


@Composable

fun SplashView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_splash_screen),
                contentDescription = null,
                modifier = Modifier
                    .size(224.dp)
                    .padding(25.dp),
            )

            Text(
                text = "Stick Together",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp) )

            Text(
                text = "Together, we make habits Stick",
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Colors.Primary
            )
        }
    }
}
