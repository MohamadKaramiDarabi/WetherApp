package ir.karami.weather.network.di

import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import ir.karami.weather.network.BuildConfig
import ir.karami.weather.network.WeatherApi
import ir.karami.weather.network.WeatherApiImpl
import kotlinx.serialization.json.Json
import okhttp3.internal.platform.android.AndroidLog

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

  @Binds
  abstract fun bindWeatherApi(weatherApiImpl: WeatherApiImpl): WeatherApi


}
@Module
@InstallIn(SingletonComponent::class)
object NetworkObjectModule {
  @Provides
  fun provideClient() = HttpClient(OkHttp) {
    engine {

    }
    defaultRequest {
      url {
        protocol = URLProtocol.HTTPS
        host = BuildConfig.WEATHER_API_HOST_NAME
        parameters.append("appid",BuildConfig.WEATHER_API_KEY)
      }

    }
    install(ContentNegotiation) {
      json(Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
      })
    }
    install(Logging) {
      level = LogLevel.ALL
      AndroidLog.enable()
    }
  }
}