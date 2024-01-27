package com.example.fitme_up.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelFragmentTag
import com.example.fitme_up.coach.adapter.CoachHomePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CoachHome : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var sharedViewModel: ViewModelFragmentTag

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_home, container, false)

        viewPager = view.findViewById(R.id.pager_coach_request)
        tabLayout = view.findViewById(R.id.tab_coach_request)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelFragmentTag::class.java)
        sharedViewModel.updateSelectedState(null)

        viewPager.adapter = CoachHomePagerAdapter(requireActivity())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                if (tab.position == 1) {
                    sharedViewModel.updateSelectedState("Tab 2")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                if (tab.position == 1){
                    sharedViewModel.updateSelectedState(null)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Pending Request"
                1 -> tab.text = "Ongoing Request" //buat nampilin accepted
            }
        }
        tabLayoutMediator.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        return view
    }

}
//    private fun getFragmentTagAtCurrentPosition(viewPager: ViewPager2): String? {
//        return if (viewPager.adapter != null) {
//            val currentFragment = viewPager.adapter!!.getItem(viewPager.currentItem)
//            currentFragment.tag
//        } else {
//            null
//        }
//    }
