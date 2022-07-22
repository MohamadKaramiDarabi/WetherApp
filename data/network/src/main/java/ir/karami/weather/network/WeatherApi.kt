package ir.karami.weather.network

import ir.karami.weather.network.data.City

interface WeatherApi {
  suspend fun searchCity(cityName: String):  List<City>
}