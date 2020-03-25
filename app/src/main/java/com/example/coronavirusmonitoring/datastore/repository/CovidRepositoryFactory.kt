package com.example.coronavirusmonitoring.datastore.repository

import android.content.Context
import com.example.coronavirusmonitoring.datastore.DataRepository

class CovidRepositoryFactory {
    companion object{
        fun createCovidRepository(context: Context): CovidRepository{
            val service = DataRepository.createCovidAPI(context)
            return CovidRepositoryImpl(service)
        }
    }
}