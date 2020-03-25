package com.example.coronavirusmonitoring.datastore.repository

import com.example.coronavirusmonitoring.datastore.model.Response
import io.reactivex.Observable


interface CovidRepository {
    fun getCovidStat(country: String): Observable<Response>
}