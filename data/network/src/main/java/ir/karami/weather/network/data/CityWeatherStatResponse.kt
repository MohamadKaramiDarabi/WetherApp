package ir.karami.weather.network.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherStatResponse(
    @SerialName("city")
    val city: CityX? = CityX(),
    @SerialName("cnt")
    val cnt: Int? = 0,
    @SerialName("cod")
    val cod: String? = "",
    @SerialName("list")
    val list: List<Data> = listOf(),
    @SerialName("message")
    val message: Double? = 0.0
)