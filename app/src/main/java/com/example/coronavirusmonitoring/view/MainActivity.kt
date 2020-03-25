package com.example.coronavirusmonitoring.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coronavirusmonitoring.R
import com.example.coronavirusmonitoring.view.viewpager.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager.adapter = MainAdapter(this, supportFragmentManager)
    }
}
