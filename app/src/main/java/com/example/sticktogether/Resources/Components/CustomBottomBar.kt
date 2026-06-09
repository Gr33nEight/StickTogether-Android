package com.example.sticktogether.Resources.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sticktogether.R

@Composable
fun CustomBottomBar(
    selectedTab: Int = 0,
    onHomeClick: () -> Unit,
    onStatsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(Colors.CardBackground)
            .padding(8.dp)
    ) {
        IconButton(
            onClick = { onHomeClick() },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                tint = if (selectedTab == 0) Colors.Primary else Colors.HeaderColor,
                modifier = Modifier.size(25.dp)
            )
        }

        IconButton(
            onClick = { onStatsClick() },
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stats),
                contentDescription = "Stats",
                tint = if (selectedTab == 1) Colors.Primary else Colors.HeaderColor
            )
        }

        IconButton(
            onClick = { onSettingsClick() },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "Settings",
                tint = if (selectedTab == 2) Colors.Primary else Colors.HeaderColor
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun CustomBottomBarPreview() {
    CustomBottomBar(
        selectedTab = 0,
        onHomeClick = {},
        onStatsClick = {},
        onSettingsClick = {}
    )
}