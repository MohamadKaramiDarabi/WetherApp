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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import com.google.accompanist.placeholder.placeholder
import ir.karami.weather.network.data.City
import ir.karami.weather.network.data.CityWeatherStatResponse
import ir.karami.weather.ui.theme.ThemeHelper

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
  cities: State<List<City>>,
  dailyWeather: State<CityWeatherStatResponse?>,
  onSearchClick: (cityName: String) -> Unit,
  onSelectCity: (city: City) -> Unit
) {
  val searchValue = remember { mutableStateOf("") }
  val hasBackButton = remember { mutableStateOf(false) }
  val focusRequester = remember { FocusRequester() }
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
          val customTextSelectionColors = TextSelectionColors(
            handleColor = Color.White.copy(0.9f),
            backgroundColor = Color.White.copy(alpha = 0.4f)
          )

          CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            OutlinedTextField(
              value = searchValue.value,
              onValueChange = {
                searchValue.value = it
              },
              placeholder = {
                Text(
                  text = "Search City Name ...",
                  color = Color.White.copy(alpha = 0.5f)
                )
              },
              shape = RoundedCornerShape(64.dp),
              leadingIcon = if (hasBackButton.value.not()) {
                {
                  IconButton(
                    onClick = {
                      hasBackButton.value = hasBackButton.value.not()
                      focusRequester.requestFocus()
                    }
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Search,
                      contentDescription = "Search Icon",
                      tint = Color.White
                    )
                  }
                }
              } else null,
              modifier = Modifier
                .fillMaxSize()
                .onFocusChanged {
                  hasBackButton.value = it.hasFocus
                }
                .focusRequester(focusRequester),
              textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
              singleLine = true,
              keyboardActions = KeyboardActions(
                onSearch = {
                  onSearchClick.invoke(searchValue.value)
                  focusManager.clearFocus()
                }
              ),
              keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                autoCorrect = true,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Search
              ),
              colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                disabledLabelColor = Color.White.copy(alpha = 0.5f),
                backgroundColor = Color.White.copy(0.1f),
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White.copy(0.5f),
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                leadingIconColor = Color.White,
                trailingIconColor = Color.White
              )
            )
          }
        }
        IconButton(
          onClick = {
            ThemeHelper.isLightThemeState.tryEmit(ThemeHelper.isLightThemeState.value.not())
          }
        ) {
          Icon(imageVector = Icons.Filled.Star, contentDescription = "Theme change icon")
        }
      }
    },
    content = {
      Column(Modifier.fillMaxSize()) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
          item { Spacer(modifier = Modifier.size(4.dp)) }
          items(cities.value) {
            Box(Modifier.padding(4.dp)) {
              OutlinedButton(
                onClick = {
                  onSelectCity.invoke(it)
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
          item { Spacer(modifier = Modifier.size(4.dp)) }
        }
        dailyWeather.value?.let { weather ->
          LazyColumn {
            item { Spacer(modifier = Modifier.size(8.dp)) }
            items(weather.list) {
              Box(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
                Card(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ) {
                  Column {
                    Text(text = it.weather?.map { it?.description }.toString())

                    SubcomposeAsyncImage(
                      model = "http://openweathermap.org/img/wn/${it.weather?.first()?.icon}@2x.png",
                      contentDescription = null,
                      modifier = Modifier.size(32.dp),
                      loading = {
                        Box(modifier =  Modifier.placeholder(true,Color.Gray))
                      },
                    )

                  }
                }
              }
              
            }
            item { Spacer(modifier = Modifier.size(8.dp)) }
          }
        }
      }
    }
  )
}