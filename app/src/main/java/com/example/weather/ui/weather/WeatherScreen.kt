package com.example.weather.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weather.data.base.BaseResponse
import com.example.weather.data.remote.model.Weather
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun WeatherScreen(modifier: Modifier = Modifier, viewModel: WeatherViewModel = hiltViewModel()) {
    val items by viewModel.weatherStateFlow.collectAsStateWithLifecycle()
    if (items is BaseResponse.Success) {
        WeatherScreen(
            items = (items as BaseResponse.Success).data,
            onSave = {},
            modifier = modifier
        )
    } else {
        Column(modifier = Modifier) {
            Text("error")
        }
    }
}

@Composable
internal fun WeatherScreen(
    items: Weather,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        var nameMyModel by remember { mutableStateOf("Compose") }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = nameMyModel,
                onValueChange = { nameMyModel = it }
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSave(nameMyModel) }) {
                Text("Save")
            }
        }
        Text("main temp: ${items.main?.temp}")
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeatherTheme {
        WeatherScreen(Weather(), onSave = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    WeatherTheme {
        WeatherScreen(Weather(), onSave = {})
    }
}