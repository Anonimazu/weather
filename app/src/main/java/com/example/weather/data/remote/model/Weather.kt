package com.example.weather.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    @SerializedName("base")
    var base: String? = "",
    @SerializedName("clouds")
    var clouds: Clouds? = Clouds(),
    @SerializedName("cod")
    var cod: Int? = 0,
    @SerializedName("coord")
    var coord: Coord? = Coord(),
    @SerializedName("dt")
    var dt: Int? = 0,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("main")
    var main: Main? = Main(),
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("rain")
    var rain: Rain? = Rain(),
    @SerializedName("sys")
    var sys: Sys? = Sys(),
    @SerializedName("timezone")
    var timezone: Int? = 0,
    @SerializedName("visibility")
    var visibility: Int? = 0,
    @SerializedName("weather")
    var weather: List<WeatherX>? = listOf(),
    @SerializedName("wind")
    var wind: Wind? = Wind()
) : Parcelable {
    val locationNameDisplay: String
        get() {
            val name = name?:"n/a"
            return "$name"
        }
    val weatherNameDisplay: String
        get() {
            val weatherName = weather?.first()?.main?:"n/a"
            return "$weatherName"
        }

    val temperatureDisplay: String
        get() {
            return "${main?.tempMax} °C / ${main?.tempMin} °C Feels like ${main?.feelsLike} °C"
        }

    val windDisplay: String
        get() {
            val speed = wind?.speed?:0.0
            return "$speed km/h"
        }

    val pressureDisplay: String
        get() {
            val pressure = main?.pressure?:0.0
            return "$pressure mb"
        }
}

@Parcelize
data class Clouds(
    @SerializedName("all")
    var all: Int? = 0
) : Parcelable

@Parcelize
data class Coord(
    @SerializedName("lat")
    var lat: String? = null,
    @SerializedName("lon")
    var lon: String? = null
) : Parcelable

@Parcelize
data class Main(
    @SerializedName("feels_like")
    var feelsLike: Double? = 0.0,
    @SerializedName("grnd_level")
    var grndLevel: Int? = 0,
    @SerializedName("humidity")
    var humidity: Int? = 0,
    @SerializedName("pressure")
    var pressure: Int? = 0,
    @SerializedName("sea_level")
    var seaLevel: Int? = 0,
    @SerializedName("temp")
    var temp: Double? = 0.0,
    @SerializedName("temp_max")
    var tempMax: Double? = 0.0,
    @SerializedName("temp_min")
    var tempMin: Double? = 0.0
) : Parcelable

@Parcelize
data class Rain(
    @SerializedName("1h")
    var h: Double? = 0.0
) : Parcelable

@Parcelize
data class Sys(
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("sunrise")
    var sunrise: Int? = 0,
    @SerializedName("sunset")
    var sunset: Int? = 0,
    @SerializedName("type")
    var type: Int? = 0
) : Parcelable

@Parcelize
data class WeatherX(
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("icon")
    var icon: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("main")
    var main: String? = ""
) : Parcelable

@Parcelize
data class Wind(
    @SerializedName("deg")
    var deg: Int? = 0,
    @SerializedName("gust")
    var gust: Double? = 0.0,
    @SerializedName("speed")
    var speed: Double? = 0.0
) : Parcelable