package com.example.weather.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.ui.theme.WeatherTheme

@Composable
internal fun LoadingComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
            color = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun LoadingComponentPreview() {
    WeatherTheme {
        LoadingComponent()
    }
}
