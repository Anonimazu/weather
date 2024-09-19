package com.example.weather.data.repository

import com.example.weather.data.base.BaseResponse
import com.example.weather.data.remote.model.GeoPosition
import com.example.weather.data.remote.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getGeoPosition(searchText: String?, limit: Int?, apiKey: String?): Flow<BaseResponse<List<GeoPosition>>>

    suspend fun getWeatherByPosition(latitude: String?, longitude: String?, apiKey: String?): Flow<BaseResponse<Weather>>

}