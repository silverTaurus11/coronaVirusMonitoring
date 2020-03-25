package com.example.coronavirusmonitoring.view.viewpager

import android.content.Context
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coronavirusmonitoring.view.CovidMonitoringListFragment


class MainAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CovidMonitoringListFragment.newInstance()
            else -> CovidMonitoringListFragment.newInstance()
        }
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return ""
    }

    override fun getCount(): Int {
        return 1
    }
}