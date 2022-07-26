package ir.karami.weather.network

import ir.karami.weather.network.data.City
import ir.karami.weather.network.data.DailyForecastResponse

interface WeatherApi {
  suspend fun searchCity(cityName: String):  List<City>
  suspend fun dailyForecastOfCity(city: City): DailyForecastResponse
}