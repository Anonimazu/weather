package com.example.weather.ui.feature.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.base.BaseResponse
import com.example.weather.data.remote.model.GeoPosition
import com.example.weather.data.remote.model.Weather
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.util.ApiKey.API_KEY_WEATHER_WORLD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    companion object {
        const val MAXIMUM_SEARCH_OFFSET = 5
    }

    private val _searchGeoResultStateFlow = MutableStateFlow<BaseResponse<List<GeoPosition>>?>(null)
    val searchGeoResultStateFlow: StateFlow<BaseResponse<List<GeoPosition>>?>
        get() = _searchGeoResultStateFlow

    private val _weatherStateFlow = MutableStateFlow<BaseResponse<Weather>?>(null)
    val weatherStateFlow: StateFlow<BaseResponse<Weather>?>
        get() = _weatherStateFlow


    init {
        // in the real application -> should get latitude and longitude from device
        getWeatherData("51.5073219", "-0.1276474")
    }

    fun getGeoPosition(searchText: String) {
        viewModelScope.launch {
            weatherRepository.getGeoPosition(
                searchText,
                MAXIMUM_SEARCH_OFFSET,
                API_KEY_WEATHER_WORLD
            ).collect { response ->
                _searchGeoResultStateFlow.value = response
                when (response) {
                    is BaseResponse.Success -> {
                        if (response.data.isNullOrEmpty()) {
                            _weatherStateFlow.value = BaseResponse.Error("Location not found! Please try again.")
                        } else {
                            val geoPosition = response.data.first { it.name?.lowercase()?.contains(searchText.lowercase()) == true }
                            val latitude = geoPosition.lat
                            val longitude = geoPosition.lon
                            getWeatherData(latitude, longitude)
                        }
                    }

                    is BaseResponse.Loading -> {
                        _weatherStateFlow.value = BaseResponse.Loading
                    }

                    is BaseResponse.Error -> {
                        _weatherStateFlow.value = BaseResponse.Error("Can't Find Your Location! Please try again.")
                    }

                    else -> {
                        _weatherStateFlow.value = BaseResponse.Error("Location not found! Please try again.")
                    }
                }

            }
        }
    }

    private fun getWeatherData(latitude: String?, longitude: String?) {
        viewModelScope.launch {
            weatherRepository.getWeatherByPosition(
                latitude,
                longitude,
                API_KEY_WEATHER_WORLD
            ).collect { response ->
                when (response) {
                    is BaseResponse.Success -> {
                        _weatherStateFlow.value = response
                    }
                    is BaseResponse.Loading -> {
                        _weatherStateFlow.value = BaseResponse.Loading
                    }
                    else -> {
                        _weatherStateFlow.value = BaseResponse.Error("Can't Find the weather! Please try again.")
                    }
                }
            }
        }
    }
}


