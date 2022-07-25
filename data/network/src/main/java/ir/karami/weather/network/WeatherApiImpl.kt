package ir.karami.weather.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import ir.karami.weather.network.data.City
import ir.karami.weather.network.data.CityWeatherStatResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class WeatherApiImpl @Inject constructor(private val client: HttpClient) : WeatherApi {
  override suspend fun searchCity(cityName: String): List<City> = client.get(EndPoints.SEARCH_CITY) {
    parameter("q", cityName)
    parameter("limit",5)
  }.body()

  override suspend fun dailyForcaseOfCity(city: City): CityWeatherStatResponse =
    client.get(EndPoints.ONE_CALL) {
      parameter("lat", city.lat)
      parameter("lon",city.lon)
      parameter("cnt", 16)
    }.body()
}