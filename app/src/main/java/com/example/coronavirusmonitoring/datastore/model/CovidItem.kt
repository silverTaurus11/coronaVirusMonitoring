package com.example.coronavirusmonitoring.datastore.model

import com.google.gson.annotations.SerializedName

data class CovidItem(
    @SerializedName("city") val city: String,
    @SerializedName("province") val province: String,
    @SerializedName("country") val country: String,
    @SerializedName("lastUpdate") val lastUpdate: String,
    @SerializedName("keyId") val keyId: String,
    @SerializedName("confirmed") val confirmed: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("recovered") val recovered: Int
)