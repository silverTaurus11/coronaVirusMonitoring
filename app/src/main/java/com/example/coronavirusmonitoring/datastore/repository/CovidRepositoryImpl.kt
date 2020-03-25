package com.example.coronavirusmonitoring.datastore.repository

import com.example.coronavirusmonitoring.datastore.Service
import com.example.coronavirusmonitoring.datastore.model.Response
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CovidRepositoryImpl(val service: Service) : CovidRepository{

    override fun getCovidStat(country: String): Observable<Response> {
        return service.getCovidStatus(country).subscribeOn(Schedulers.io())
    }

}