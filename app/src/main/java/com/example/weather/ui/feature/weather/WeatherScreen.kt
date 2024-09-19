package com.example.weather.ui.feature.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weather.data.base.BaseResponse
import com.example.weather.data.remote.model.Main
import com.example.weather.data.remote.model.Weather
import com.example.weather.data.remote.model.WeatherX
import com.example.weather.data.remote.model.Wind
import com.example.weather.ui.component.ErrorComponent
import com.example.weather.ui.component.GlassMorphismCard
import com.example.weather.ui.component.LoadingComponent
import com.example.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(modifier: Modifier = Modifier, viewModel: WeatherViewModel = hiltViewModel()) {
    val weatherUiState by viewModel.weatherStateFlow.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Weather")
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                WeatherScreen(
                    weatherUiState = weatherUiState,
                    onSearch = { locationName ->
                        viewModel.getGeoPosition(locationName)
                    }
                )
            }
        }
    )

}

@Composable
internal fun WeatherScreen(
    weatherUiState: BaseResponse<Weather>?,
    onSearch: (locationName: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C3E50), Color(0xFF34495E), Color(0xFF4B79A1))
                )
            )
            .padding(16.dp)
    ) {
        var nameMyModel by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                modifier = Modifier.weight(2f),
                value = nameMyModel,
                onValueChange = { nameMyModel = it }
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSearch(nameMyModel) }) {
                Text("Search")
            }
        }
        when (weatherUiState) {
            is BaseResponse.Success -> WeatherContent(weatherUiState.data)
            is BaseResponse.Error -> ErrorComponent(weatherUiState.message)
            is BaseResponse.Loading -> LoadingComponent()
            else -> WeatherIdleScreen("")
        }
    }
}

@Composable
internal fun WeatherContent(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(16.dp),
            text = weather.locationNameDisplay,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        GlassMorphismCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = weather.weatherNameDisplay,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = weather.temperatureDisplay,
                    color = Color.White
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GlassMorphismCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Wind",
                        color = Color.White,
                        fontSize = 32.sp
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = weather.windDisplay,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            GlassMorphismCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        text = "Pressure",
                        color = Color.White,
                        fontSize = 32.sp
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = weather.pressureDisplay,
                        color = Color.White
                    )
                }
            }

        }
    }


}


@Composable
internal fun WeatherIdleScreen(message: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            fontSize = 30.sp,
            color = Color.LightGray
        )
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeatherTheme {
        WeatherScreen(
            BaseResponse.Success(
                Weather(
                    name = "Bangkok",
                    main = Main(temp = 25.0, tempMax = 36.0, tempMin = 24.0, feelsLike = 24.5, pressure = 1234),
                    wind = Wind(speed = 1.8),
                    weather = listOf(WeatherX(main = "Clouds"))
                )
            ),
            onSearch = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    WeatherTheme {
        WeatherScreen(
            BaseResponse.Success(
                Weather(
                    name = "Bangkok",
                    main = Main(temp = 25.0, tempMax = 36.0, tempMin = 24.0, feelsLike = 24.5, pressure = 1234),
                    wind = Wind(speed = 1.8),
                    weather = listOf(WeatherX(main = "Clouds"))
                )
            ),
            onSearch = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun WeatherScreenErrorPreview() {
    WeatherTheme {
        WeatherScreen(BaseResponse.Error(message = "Error"), onSearch = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun WeatherScreenLoadingPreview() {
    WeatherTheme {
        WeatherScreen(BaseResponse.Loading, onSearch = {})
    }
}