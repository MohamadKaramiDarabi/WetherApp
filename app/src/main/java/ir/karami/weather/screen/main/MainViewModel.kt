package ir.karami.weather.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.karami.weather.network.data.City
import ir.karami.weather.network.data.DailyForecastResponse
import ir.karami.weather.repository.weather.WeatherRepository
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ContainerHost<CityScreenState, CityScreenSideEffect>,ViewModel() {
  override val container = container<CityScreenState, CityScreenSideEffect>(CityScreenState())
  fun searchCity(cityName: String) = intent {
    if (cityName.isEmpty()) return@intent
    reduce {
      state.copy(cityListState = CityListState.Loading, dailyWeather = DailyForecastState.Idle)
    }
    delay(500)
    repository.searchCityByName(cityName).collect {
      reduce {
        state.copy(cityListState = CityListState.List(it))
      }
    }
  }
  fun getDailyWeather(city: City) = intent {
    reduce {
      state.copy(dailyWeather = DailyForecastState.Loading)
    }
    delay(500)
    repository.cityDairyForecast(city).collect {
      reduce {
        state.copy(dailyWeather = DailyForecastState.DailyForecast(it))
      }
    }
  }
}
sealed interface CityScreenSideEffect {

}
data class CityScreenState(
  val cityListState: CityListState = CityListState.Idle,
  val dailyWeather: DailyForecastState = DailyForecastState.Idle
)

sealed interface CityListState {
  data class List(val cities: kotlin.collections.List<City>) : CityListState
  object Loading: CityListState
  object Idle: CityListState
}
sealed interface DailyForecastState {
  data class DailyForecast(val dailyForecastResponse: DailyForecastResponse) : DailyForecastState
  object Loading: DailyForecastState
  object Idle: DailyForecastState
}