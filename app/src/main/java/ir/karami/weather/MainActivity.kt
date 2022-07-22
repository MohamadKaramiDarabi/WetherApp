package ir.karami.weather


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ir.karami.weather.network.data.City
import ir.karami.weather.repository.weather.WeatherRepository
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
      val list = weatherRepository.searchCityByName("sarab")
      rememberCoroutineScope().launch {
        list.collectLatest {
          println(it.toString())
        }
      }
      WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Greeting(list.collectAsState(initial = listOf()))
        }
      }
    }
  }
}

@Composable
fun Greeting(name: State<List<City>>) {
  Text(text = "Hello ${name.value}!")
}