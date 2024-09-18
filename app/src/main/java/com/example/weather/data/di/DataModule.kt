package com.example.weather.data.di

import com.example.weather.data.remote.service.WeatherService
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideWeatherService(
        retrofit: Retrofit
    ): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        weatherService: WeatherService
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherService)
    }

}