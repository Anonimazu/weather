package com.example.weather.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.net.CookieManager
import java.net.CookiePolicy
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private val DATE_FORMATS = arrayOf(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy-MM",
            "yyyy"
        )
    }

    private class DateDeserializer : JsonDeserializer<Date> {
        @Throws(JsonParseException::class)
        override fun deserialize(jsonElement: JsonElement, typeOF: Type, context: JsonDeserializationContext): Date {
            val format = when (jsonElement.asString.length) {
                4 -> DATE_FORMATS[5]
                7 -> DATE_FORMATS[4]
                10 -> DATE_FORMATS[3]
                19 -> DATE_FORMATS[2]
                20 -> DATE_FORMATS[1]
                24 -> DATE_FORMATS[0]
                else -> ""
            }
            try {
                val formatter = SimpleDateFormat(format, Locale.US)
                formatter.timeZone = TimeZone.getTimeZone("UTC")
                formatter.isLenient = false
                return formatter.parse(jsonElement.asString)
            } catch (e: ParseException) {
                throw JsonParseException("Unparseable date: \"" + jsonElement.asString + "\". Supported formats: " + DATE_FORMATS.contentToString())
            }
        }
    }



    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val basic = HttpLoggingInterceptor()
        basic.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val header = HttpLoggingInterceptor()
        header.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val body = HttpLoggingInterceptor()
        body.setLevel(HttpLoggingInterceptor.Level.BODY)

        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val client = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(cookieManager))
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)


        client.addInterceptor(basic)
            .addInterceptor(header)
            .addInterceptor(body)
            .addInterceptor(chuckerInterceptor)


        return client.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(context, true, RetentionManager.Period.ONE_HOUR)
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

}