package com.example.weather.data.repository

import com.example.weather.data.base.BaseResponse
import com.example.weather.data.remote.model.GeoPosition
import com.example.weather.data.remote.model.Weather
import com.example.weather.data.remote.service.WeatherService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {
    override suspend fun getGeoPosition(searchText: String?, limit: Int?, apiKey: String?): Flow<BaseResponse<List<GeoPosition>>> {
        return flow {
            // Emit loading state
            emit(BaseResponse.Loading)
            try {
                val response = weatherService.getGeoPosition(searchText, limit, apiKey)
                // Emit success state with data
                emit(BaseResponse.Success(response))
            } catch (e: Exception) {
                // Emit error state with message and exception
                emit(BaseResponse.Error("Failed to fetch data", e))
            }
        }
    }

    override suspend fun getWeatherByPosition(latitude: String?, longitude: String?, apiKey: String?): Flow<BaseResponse<Weather>> {
        return flow {
            // Emit loading state
            emit(BaseResponse.Loading)
            try {
                val response = weatherService.getWeatherByPosition(latitude, longitude, "metric", apiKey)
                // Emit success state with data
                emit(BaseResponse.Success(response))
            } catch (e: Exception) {
                // Emit error state with message and exception
                emit(BaseResponse.Error("Failed to fetch data", e))
            }
        }
    }


}
