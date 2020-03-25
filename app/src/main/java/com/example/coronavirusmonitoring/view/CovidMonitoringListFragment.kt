package com.example.coronavirusmonitoring.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronavirusmonitoring.R
import com.example.coronavirusmonitoring.datastore.model.CovidItem
import com.example.coronavirusmonitoring.datastore.repository.CovidRepositoryFactory
import com.example.coronavirusmonitoring.view.presenter.CovidMonitoringPresenterImpl
import kotlinx.android.synthetic.main.fragment_covid_monitoring.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.loading_layout.*


class CovidMonitoringListFragment(private val contentLayoutId: Int) : Fragment(contentLayoutId), CovidMonitoringView {
    companion object{
        fun newInstance(): CovidMonitoringListFragment{
            return CovidMonitoringListFragment(R.layout.fragment_covid_monitoring)
        }
    }
    private val USERNAME = "Gayuh Nurul Huda"
    private val dateFormat = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")

    private lateinit var lastCountry: String
    private var covidRecyclerView: RecyclerView ?= null
    private val covidRecyclerAdapter by lazy { CovidRecyclerViewAdapter() }
    private val covidPresenter by lazy { CovidMonitoringPresenterImpl(this,
        CovidRepositoryFactory.createCovidRepository(activity!!.applicationContext)) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(contentLayoutId, container, false) as ViewGroup
        covidRecyclerView = view.findViewById(R.id.covid_list)
        initRecycleView()
        return view
    }

    private fun initRecycleView(){
        covidRecyclerView?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        covidRecyclerView?.adapter = covidRecyclerAdapter
        val dividerItemDecoration = DividerItemDecoration(activity?.applicationContext, VERTICAL)
        covidRecyclerView?.addItemDecoration(dividerItemDecoration)
        covidRecyclerView?.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateHeaderInformation("-", 0, "")
        initInputCountry()
        requestDataCovid("Indonesia")
        initListener()
    }

    private fun requestDataCovid(country: String){
        covidPresenter.getCovidData(country)
        entryCountryEditText?.hideKeyboard()
    }

    private fun initListener(){
        greetingLabel.text = getString(R.string.general_welcome).replace("$1", USERNAME)
        refreshButton.setOnClickListener {
            if(lastCountry.isNotEmpty() && lastCountry.isNotBlank()){
                requestDataCovid(lastCountry)
            }
        }
    }

    override fun showCovidData(covidDatas: List<CovidItem>) {
        activity?.runOnUiThread {
            covidRecyclerAdapter.updateItemList(covidDatas)
        }
    }

    override fun updateHeaderInformation(country: String, totalData: Int, lastUpdate: String){
        activity?.runOnUiThread {
            lastCountry = country
            mainCountryLabel.text = getString(R.string.general_country_form).replace("$1", country)
            totalDataLabel.text = getString(R.string.general_total_data).replace("$1", totalData.toString())
            if(!lastUpdate.equals("", true)){
                lastUpdateLabel.text = getString(R.string.general_last_update).replace("$1",
                    dateFormat.print(DateTime(lastUpdate)))
            } else{
                lastUpdateLabel.text = getString(R.string.general_last_update).replace("$1", "-")
            }
        }
    }

    private fun initInputCountry(){
        entryCountryEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val text = entryCountryEditText.text.toString()
                if(text.isNotBlank() && text.isNotEmpty()){
                    requestDataCovid(entryCountryEditText.text.toString())
                }
                true
            } else false
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun showEmptyInformation() {
        activity?.runOnUiThread {
            updateHeaderInformation("-", 0, "")
            Toast.makeText(context, activity?.resources?.getString(R.string.general_toast_input_error),
                Toast.LENGTH_LONG).show()
            showCovidData(arrayListOf())
        }
    }

    override fun showLoading() {
        activity?.runOnUiThread {
            loading_container?.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        activity?.runOnUiThread {
            loading_container?.visibility = View.GONE
        }
    }
}