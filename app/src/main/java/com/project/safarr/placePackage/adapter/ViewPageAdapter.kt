package com.project.safarr.placePackage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.safarr.placePackage.LogFragment
import com.project.safarr.placePackage.OverviewFragment
import com.project.safarr.placePackage.SafetyFragment

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OverviewFragment()
            }
            1 -> {
                SafetyFragment()
            }
            2 -> {
                LogFragment()
            }
            else -> {
                Fragment()
            }

        }
    }
}