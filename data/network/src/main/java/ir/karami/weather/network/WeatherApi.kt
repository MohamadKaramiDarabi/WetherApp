package ir.karami.weather.network

import ir.karami.weather.network.data.City
import ir.karami.weather.network.data.CityWeatherStatResponse

interface WeatherApi {
  suspend fun searchCity(cityName: String):  List<City>
  suspend fun dailyForcaseOfCity(city: City): CityWeatherStatResponse
}