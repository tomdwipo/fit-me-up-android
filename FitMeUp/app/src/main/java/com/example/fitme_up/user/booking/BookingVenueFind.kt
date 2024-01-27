package com.example.fitme_up.user.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.bookingAdapter.BookingVenuePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BookingVenueFind : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_find, container, false)

        viewPager = view.findViewById(R.id.pager_book_find_venue)
        tabLayout = view.findViewById(R.id.tab_book_find_venue)

        viewPager.adapter = BookingVenuePagerAdapter(requireActivity())
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Nearby Venue"
                1 -> tab.text = "Available Venue"
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = fragmentManager.backStackEntryCount
            if (backStackCount > 0) {

                val fm = fragmentManager.getBackStackEntryAt(backStackCount - 1)
                val prevFragmentStack = fm.name

                val fm2 = fragmentManager.findFragmentByTag("lfg_details")

                if (fm2 != null) {
                    Log.d("print", "from lfg details")
                    addFragmentWithTag(this, "lfg_find_venue")
//                    Toast.makeText(activity, "prev fragment lfg details", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}