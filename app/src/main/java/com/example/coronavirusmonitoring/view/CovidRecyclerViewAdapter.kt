package com.example.coronavirusmonitoring.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coronavirusmonitoring.R
import com.example.coronavirusmonitoring.datastore.model.CovidItem

class CovidRecyclerViewAdapter: RecyclerView.Adapter<ViewHolder>() {
    private var covidItems: ArrayList<CovidItem> = arrayListOf()


    fun updateItemList(covidItems: List<CovidItem>){
        this.covidItems.clear()
        this.covidItems.addAll(ArrayList(covidItems))
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.covid_monitoring_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return covidItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(covidItems[position], position)
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val provinceLabel by lazy { view.findViewById<TextView>(R.id.provinceLabel) }
    private val countryLabel by lazy { view.findViewById<TextView>(R.id.countryLabel) }
    private val positiveLabel by lazy { view.findViewById<TextView>(R.id.positiveLabel) }
    private val deathLabel by lazy { view.findViewById<TextView>(R.id.deathLabel) }
    private val recoveredLabel by lazy { view.findViewById<TextView>(R.id.recoveredLabel) }

    fun bind(covidItem: CovidItem, position: Int) {
        provinceLabel.text = covidItem.province
        countryLabel.text = covidItem.country
        positiveLabel.text = covidItem.confirmed.toString()
        deathLabel.text = covidItem.deaths.toString()
        recoveredLabel.text = covidItem.recovered.toString()
    }
}