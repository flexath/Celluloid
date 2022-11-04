package com.flexath.celluloid.ui.saved

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.flexath.celluloid.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SavedFirstFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = requireActivity().findViewById(R.id.savedFirstTabLayout)
        viewPager = requireActivity().findViewById(R.id.savedFirstViewPager)
        viewPager.adapter = ViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabLayout,viewPager) { tab,index ->
            tab.text = when(index) {
                0 -> "Movies"
                1 -> "Series"
                else -> throw NotFoundException("Saved Fragment Not Found!!")
            }
        }.attach()
    }
}