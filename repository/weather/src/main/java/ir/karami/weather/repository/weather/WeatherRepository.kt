package ir.karami.weather.repository.weather

import ir.karami.weather.network.WeatherApi
import ir.karami.weather.network.data.City
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {
  fun searchCityByName(cityName: String) = flow {
    if(cityName.isEmpty()) throw Throwable("empty city name")
    emit(weatherApi.searchCity(cityName))
  }.catch {
    it.printStackTrace()
  }

  fun cityDairyForecast(city: City?) = flow {
    if (city==null) throw NullPointerException()
    emit(weatherApi.dailyForecastOfCity(city))
  }.catch {
    it.printStackTrace()
  }
}