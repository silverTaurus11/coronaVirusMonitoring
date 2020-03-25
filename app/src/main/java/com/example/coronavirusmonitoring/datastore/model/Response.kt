package com.example.coronavirusmonitoring.datastore.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("error") val error: Boolean,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CovidDataResponse
    )

data class CovidDataResponse(
    @SerializedName("lastChecked") val lastChecked: String,
    @SerializedName("covid19Stats") val covid19Stats: List<CovidItem>
    )
