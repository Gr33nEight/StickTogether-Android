package com.example.sticktogether.Features.auth.register.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.Resources.Components.Colors
import org.w3c.dom.Text

@Composable

fun RegisterFooter(
    onLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Already have account?",
            color = Color.White,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "Login Now",
            color = Colors.Secondary,
            fontSize = 13.sp,
            fontWeight = FontWeight.W300,
            textDecoration = TextDecoration.Underline,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .clickable { onLoginClick() }
        )
    }
}