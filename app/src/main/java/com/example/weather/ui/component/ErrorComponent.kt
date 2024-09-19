package com.example.weather.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.weather.ui.theme.WeatherTheme

@Composable
internal fun ErrorComponent(errorMessage: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            fontSize = 30.sp,
            color = Color.LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorComponentPreview() {
    WeatherTheme {
        ErrorComponent("Please Try Again")
    }
}