package ir.karami.weather.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import ir.karami.weather.screen.main.component.SearchTextInput
import ir.karami.weather.screen.main.component.WeatherInfo
import ir.karami.weather.ui.theme.ThemeHelper
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun MainScreen(
  mainViewModel: MainViewModel
) {
  val state by mainViewModel.container.stateFlow.collectAsState()

  val hasBackButton = remember { mutableStateOf(false) }
  val focusManager = LocalFocusManager.current
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopAppBar(modifier = Modifier.height(68.dp)) {
        if (hasBackButton.value) {
          IconButton(
            onClick = {
              focusManager.clearFocus()
            },
            enabled = true,
            modifier = Modifier
              .background(Color.Transparent, CircleShape)
              .clip(CircleShape),

            ) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "back button",
              tint = Color.White
            )
          }
        }
        Box(
          modifier = Modifier
            .weight(1f)
            .width(0.dp)
            .padding(8.dp)
        ) {
          SearchTextInput(
            hasBackButton = hasBackButton, 
            focusManager = focusManager, 
            onSearchButtonClick = {
            mainViewModel.searchCity(it)
          })
        }
        IconButton(
          onClick = {
            ThemeHelper.isLightThemeState.tryEmit(ThemeHelper.isLightThemeState.value.not())
          }
        ) {
          Icon(imageVector = if(ThemeHelper.isLightThemeState.collectAsState().value) Icons.Outlined.DarkMode else Icons.Outlined.LightMode, contentDescription = "Theme change icon")
        }
      }
    },
    content = {
      Column(
        Modifier
          .fillMaxSize()
          .background(color = Color.Gray.copy(alpha = 0.05f))) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
          item { Spacer(modifier = Modifier.size(4.dp)) }
          when (val cityStateList = state.cityListState) {
            CityListState.Idle -> Unit
            is CityListState.List -> items(cityStateList.cities) {
              Box(Modifier.padding(4.dp)) {
                OutlinedButton(
                  onClick = {
                    mainViewModel.getDailyWeather(it)
                  },
                  shape = RoundedCornerShape(24.dp)
                ) {
                  Text(
                    text = it.name ?: "",
                    color = MaterialTheme.colors.onBackground
                  )
                }
              }
            }
            CityListState.Loading -> items(4) {
              Box(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)) {
                Box(
                  modifier = Modifier
                    .placeholder(
                      visible = true,
                      highlight = PlaceholderHighlight.shimmer(),
                      color = Color.Gray.copy(0.5f),
                      shape = RoundedCornerShape(24.dp)
                    )
                    .width(100.dp)
                    .height(40.dp)
                )
              }
            }
          }
          item { Spacer(modifier = Modifier.size(4.dp)) }
        }
        state.dailyWeather.let { weather ->
          when (weather) {
            is DailyForecastState.DailyForecast -> LazyColumn {
              item { Spacer(modifier = Modifier.size(8.dp)) }
              items(weather.dailyForecastResponse.list) {
                WeatherInfo(data = it)
              }
              item { Spacer(modifier = Modifier.size(8.dp)) }
            }
            DailyForecastState.Idle -> Unit
            DailyForecastState.Loading -> LazyColumn {
              item { Spacer(modifier = Modifier.size(8.dp)) }
              items(10) {
                Box(
                  modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                ) {
                  Card(
                    modifier = Modifier
                      .fillMaxWidth()
                      .height(64.dp)
                      .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(),
                        color = Color.Gray.copy(0.5f),
                        shape = RoundedCornerShape(8.dp)
                      )
                      .padding(8.dp)
                  ) {}
                }
              }
              item { Spacer(modifier = Modifier.size(8.dp)) }
            }
          }
        }
      }
    }
  )
}