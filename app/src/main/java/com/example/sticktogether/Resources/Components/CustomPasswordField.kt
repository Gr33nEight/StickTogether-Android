package com.example.sticktogether.Resources.Components

import android.view.RoundedCorner
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sticktogether.R

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int? = null,
    isPassword: Boolean = false,
    isError: Boolean = false,
) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.White,
                fontSize = 16.sp
            )
        },

        leadingIcon = leadingIcon?.let { IconResId ->
            {
                Icon(
                    painter = painterResource(IconResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )
            }
        },

        trailingIcon = {
            if (isPassword) {
                val icon = if (passwordVisible) R.drawable.ic_visible else R.drawable.ic_visible_off

                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },

        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Colors.BackgroundColor,
            unfocusedContainerColor = Colors.BackgroundColor,
            focusedBorderColor = Colors.Primary,
            unfocusedBorderColor = Colors.Primary,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorBorderColor = Colors.Error,
            errorTextColor = Color.White
        )
    )
}

