package com.example.sticktogether.Features.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R
import com.example.sticktogether.Resources.Components.Colors

@Composable
fun HabitCard(
    title: String,
    time: String,
    isCompleted: Boolean = false,
    onToggleCompleted: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Colors.CardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Colors.Primary.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = null,
                tint = Colors.Primary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = Colors.HeaderColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = time,
                color = Colors.TextGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_circle_outline),
            contentDescription = null,
            tint = if (isCompleted) Colors.Primary else Colors.TextGray,
            modifier = Modifier
                .size(28.dp)
                .clickable { onToggleCompleted() }
        )
        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Usuń nawyk",
            tint = Color.Red,
            modifier = Modifier
                .size(28.dp)
                .clickable { onDeleteClick() }
        )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun HabitCardPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HabitCard(
            title = "Morning Run",
            time = "07:00 AM",
            isCompleted = false,
            onToggleCompleted = {},
            onDeleteClick = {}
        )
        HabitCard(
            title = "Read a Book",
            time = "08:00 PM",
            isCompleted = true,
            onToggleCompleted = {},
            onDeleteClick = {}
        )
    }
}