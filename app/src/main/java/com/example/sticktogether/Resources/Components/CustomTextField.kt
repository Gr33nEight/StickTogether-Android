package com.example.sticktogether.Resources.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),

        placeholder = {
            Text(
                text = placeholder,
                color = Color.White,
                fontSize = 16.sp
            )
        },

        leadingIcon = leadingIcon?.let { iconResId ->
            {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White, //
            unfocusedContainerColor = Color.White, //
            focusedBorderColor = Color.White, //
            unfocusedBorderColor = Color.White, //
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        )
    )
}