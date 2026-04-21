package com.example.sticktogether.Features.auth.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.Resources.Components.Colors

@Composable

fun RegisterHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Create Account",
            color = Colors.HeaderColor,
            fontSize = 32.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Create your new Stick Together account and get started with us",
            color = Colors.HeaderColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 30.dp),
            thickness = 0.5.dp,
            color = Colors.HorizontalDivider
        )
    }
}