package ir.karami.weather


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ir.karami.weather.network.data.City
import ir.karami.weather.repository.weather.WeatherRepository
import ir.karami.weather.screen.main.MainScreen
import ir.karami.weather.ui.theme.ThemeHelper
import ir.karami.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var weatherRepository: WeatherRepository
  @SuppressLint("CoroutineCreationDuringComposition")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      var searchedName by remember {
        mutableStateOf("")
      }
      var city : City? by remember {
        mutableStateOf(null)
      }
      WeatherTheme(darkTheme = ThemeHelper.isLightThemeState.collectAsState()) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          MainScreen(
            weatherRepository.searchCityByName(searchedName).collectAsState(initial = listOf()),
            weatherRepository.cityDairyForecast(city).collectAsState(initial = null),
            onSearchClick = {
              searchedName = it
            },
            onSelectCity = {
              city = it
            }
          )
        }
      }
    }
  }
}

