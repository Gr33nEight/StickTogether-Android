package com.example.sticktogether.Resources.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .background(Colors.CardBackground.copy(alpha = 0.8f))
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(100.dp))
                .background(if (selectedTab == 0) Colors.Primary else Color.Transparent)
                .clickable { onHomeClick() }
                .padding(horizontal = 20.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+ New habit",
                color = if (selectedTab == 0) Colors.BackgroundColor else Colors.HeaderColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(
            onClick = { onStatsClick() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stats),
                contentDescription = null,
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
                contentDescription = null,
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