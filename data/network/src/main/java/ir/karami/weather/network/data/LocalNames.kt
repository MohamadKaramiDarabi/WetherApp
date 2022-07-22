package ir.karami.weather.network.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalNames(
    @SerialName("en")
    val en: String,
    @SerialName("fa")
    val fa: String?,
    @SerialName("feature_name")
    val featureName: String?=null,
)