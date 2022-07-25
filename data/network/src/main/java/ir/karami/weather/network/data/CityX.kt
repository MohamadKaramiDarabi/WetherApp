package ir.karami.weather.network.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityX(
    @SerialName("coord")
    val coord: Coord? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("population")
    val population: Int? = null,
    @SerialName("timezone")
    val timezone: Int? = null
)