package ir.karami.weather.network.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
//    @SerialName("clouds")
//    val clouds: Int? = null,
    @SerialName("deg")
    val deg: Int? = null,
    @SerialName("dt")
    val dt: Int? = null,
    @SerialName("feels_like")
    val feelsLike: FeelsLike? = null,
    @SerialName("gust")
    val gust: Double? = null,
    @SerialName("humidity")
    val humidity: Int? = null,
    @SerialName("pop")
    val pop: Double? = null,
    @SerialName("pressure")
    val pressure: Double? = null,
    @SerialName("speed")
    val speed: Double? = null,
    @SerialName("sunrise")
    val sunrise: Int? = null,
    @SerialName("sunset")
    val sunset: Int? = null,
    @SerialName("temp")
    val temp: Temp? = null,
    @SerialName("weather")
    val weather: List<Weather?>? = null
)