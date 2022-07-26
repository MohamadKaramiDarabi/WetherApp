package ir.karami.weather.network.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyForecastResponse(
    @SerialName("city")
    val city: CityX? = CityX(),
    @SerialName("cnt")
    val cnt: Int? = 0,
    @SerialName("cod")
    val cod: String? = "",
    @SerialName("list")
    val list: List<Data> = listOf(),
    @SerialName("message")
    val message: Int? = 0
)