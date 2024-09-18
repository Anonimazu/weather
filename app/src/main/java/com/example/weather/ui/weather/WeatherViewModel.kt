package com.example.weather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.base.BaseResponse
import com.example.weather.data.remote.model.GeoPosition
import com.example.weather.data.remote.model.Weather
import com.example.weather.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _searchGeoResultStateFlow = MutableStateFlow<BaseResponse<List<GeoPosition>>?>(null)
    val searchGeoResultStateFlow: StateFlow<BaseResponse<List<GeoPosition>>?>
        get() = _searchGeoResultStateFlow

    private val _weatherStateFlow = MutableStateFlow<BaseResponse<Weather>?>(null)
    val weatherStateFlow: StateFlow<BaseResponse<Weather>?>
        get() = _weatherStateFlow


    /*val uiState: StateFlow<MyModelUiState> = myModelRepository
        .myModels.map<List<String>, MyModelUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyModelUiState.Loading)

    fun addMyModel(name: String) {
        viewModelScope.launch {
            myModelRepository.add(name)
        }
    }*/

    init {
        getGeoPosition("")
    }

    fun getGeoPosition(searchText: String){
        viewModelScope.launch {
            weatherRepository.getWeatherByPosition(
                51.5073219,
                -0.1276474,
                "206d18d27f4e79317bc6d8e3facd3b75"
            ).collect { response ->
                _weatherStateFlow.value = response
            }
        }
    }
}
