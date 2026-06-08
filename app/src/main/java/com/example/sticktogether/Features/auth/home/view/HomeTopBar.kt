package com.example.sticktogether.Features.auth.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors

@Composable
fun HomeTopBar(
    userName: String = "Natanael",
    onNotificationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Good Morning, $userName \uD83D\uDC4B",
            color = Colors.HeaderColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )

        IconButton(onClick = { onNotificationClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "Notifications",
                tint = Colors.HeaderColor
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(
        userName = "Natanael",
        onNotificationClick = {}
    )
}