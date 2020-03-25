package com.example.coronavirusmonitoring.view

import com.example.coronavirusmonitoring.datastore.model.CovidItem

interface CovidMonitoringView {
    fun showCovidData(covidDatas: List<CovidItem>)
    fun updateHeaderInformation(country: String, totalData: Int, lastUpdate: String)
    fun showLoading()
    fun hideLoading()
    fun showEmptyInformation()
}