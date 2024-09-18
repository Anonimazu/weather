package com.example.weather.data.remote.service

import com.example.weather.data.remote.model.GeoPosition
import com.example.weather.data.remote.model.Weather
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("geo/1.0/direct")
    suspend fun getGeoPosition(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
        @Query("appid") apiKey: String?
    ) : List<GeoPosition>

    @GET("data/2.5/weather")
    suspend fun getWeatherByPosition(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("appid") apiKey: String?
    ) : Weather
}