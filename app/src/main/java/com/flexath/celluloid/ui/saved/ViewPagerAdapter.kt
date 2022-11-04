package com.flexath.celluloid.ui.saved

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment:FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MovieSavedFirstFragment()
            1 -> TvShowSavedFirstFragment()
            else -> throw NotFoundException("Saved Fragment Not Found!")
        }
    }
}