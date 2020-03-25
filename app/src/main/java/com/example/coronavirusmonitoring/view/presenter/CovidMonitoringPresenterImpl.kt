package com.example.coronavirusmonitoring.view.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.coronavirusmonitoring.datastore.repository.CovidRepository
import com.example.coronavirusmonitoring.view.CovidMonitoringView
import io.reactivex.schedulers.Schedulers

class CovidMonitoringPresenterImpl(private val view: CovidMonitoringView,
                                   private val repository: CovidRepository): CovidMonitoringPresenter {

    @SuppressLint("CheckResult")
    override fun getCovidData(country: String?) {
        capitalize(country?.toLowerCase())?.let {
            view.showLoading()
            repository.getCovidStat(it)
                .observeOn(Schedulers.computation())
                .subscribe ({
                    if(!it.error){
                        val covid19Stats = it.data.covid19Stats
                        if(covid19Stats.isNotEmpty()){
                            val firstCountryName = covid19Stats[0].country
                            if(firstCountryName.equals(country?.toLowerCase(), true)){
                                view.updateHeaderInformation(capitalize(firstCountryName).toString(),
                                    covid19Stats.size, it.data.lastChecked)
                                view.showCovidData(covid19Stats)
                            } else{
                                view.showEmptyInformation()
                            }
                        } else{
                            view.showEmptyInformation()
                        }
                    } else{
                        view.showEmptyInformation()
                    }
                    view.hideLoading()
                }, {
                    Log.e("ErrorCovid", it.message)
                    view.hideLoading()
                })
        }
    }

    private fun capitalize(str: String?): String? {
        return if (str == null || str.isEmpty()) {
            str
        } else str.substring(0, 1).toUpperCase() + str.substring(1)

    }

}