package com.example.coronavirusmonitoring.datastore

import com.example.coronavirusmonitoring.datastore.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {
    @Headers("Content-Type: application/json")
    @GET("/v1/stats")
    fun getCovidStatus(
        @Query("country") country:String):Observable<Response>
}