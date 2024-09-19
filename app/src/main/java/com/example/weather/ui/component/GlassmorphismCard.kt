package com.example.weather.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun GlassMorphismCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    blurAmount: Float = 0.1F,
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(Color.White.copy(alpha = blurAmount))
            .padding(16.dp),
    ) {
        content()
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF4B79A1)
@Composable
private fun DefaultPreview() {
    WeatherTheme {
        GlassMorphismCard(
            content = {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Glass Morphism Card", color = Color.White)
                }
            }
        )
    }
}