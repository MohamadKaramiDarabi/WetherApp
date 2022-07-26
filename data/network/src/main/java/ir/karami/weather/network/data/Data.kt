package ir.karami.weather.network.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("clouds")
    val clouds: Clouds? = null,
    @SerialName("dt")
    val dt: Int? = null,
    @SerialName("dt_txt")
    val dtTxt: String? = null,
    @SerialName("main")
    val main: Main? = null,
    @SerialName("pop")
    val pop: Float? = null,
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("visibility")
    val visibility: Int? = null,
    @SerialName("weather")
    val weather: List<Weather?>? = null,
    @SerialName("wind")
    val wind: Wind? = null
)