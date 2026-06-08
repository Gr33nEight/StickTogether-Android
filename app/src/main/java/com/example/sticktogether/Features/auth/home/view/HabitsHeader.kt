package com.example.sticktogether.Features.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors

@Composable
fun HabitsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Track your habit",
            color = Colors.HeaderColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Colors.CardBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = Colors.HeaderColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun HabitsHeaderPreview() {
    HabitsHeader()
}