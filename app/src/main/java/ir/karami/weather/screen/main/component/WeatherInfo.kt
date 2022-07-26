package ir.karami.weather.screen.main.component

import android.annotation.SuppressLint
import android.icu.util.TimeZone
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.placeholder.placeholder
import ir.karami.weather.network.data.Data
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun WeatherInfo(data: Data) {
  Box(modifier = Modifier
    .padding(8.dp)
    .fillMaxWidth()) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp),
      shape = RoundedCornerShape(8.dp)
    ) {
      Row(
        Modifier
          .padding(16.dp)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        SubcomposeAsyncImage(
          model = "http://openweathermap.org/img/wn/${data.weather?.first()?.icon}@2x.png",
          contentDescription = null,
          modifier = Modifier.size(32.dp),
          loading = {
            Box(modifier = Modifier.placeholder(true, Color.Gray))
          }
        )
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = data.dtTxt?.let { dateFormat.parse(it) }
        val calendar = Calendar.getInstance()
        date?.time?.let {
          calendar.timeInMillis = it
        }
        val dayNumber = calendar.get(Calendar.DAY_OF_WEEK)
        val days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" )
        val day = days[dayNumber]

        Spacer(modifier = Modifier.size(8.dp))
        Text(
          text =
          (day.plus(" - ").plus(date?.hours.toString())
            .plus(":").plus(date?.minutes) ?: "")
            .plus(" - ")
            .plus(data.weather?.firstOrNull()?.description.toString())
        )
        Box(
          modifier = Modifier
            .width(0.dp)
            .weight(1f)
        )
        Text(text = data.main?.tempMax?.toString()
          ?.plus("°")
          ?.plus(" / ")
          ?.plus(data.main?.tempMin?.toString())
          ?.plus("°")
          ?: ""
        )
        
      }
    }
  }
}