package ir.karami.weather.network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    @SerialName("country")
    val country: String? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("local_names")
    val localNames: LocalNames? = null,
    @SerialName("lon")
    val lon: Double? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("state")
    val state: String? = null
)
