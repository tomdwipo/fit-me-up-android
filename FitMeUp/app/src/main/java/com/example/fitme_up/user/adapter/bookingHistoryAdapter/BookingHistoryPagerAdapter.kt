package com.example.fitme_up.user.adapter.bookingHistoryAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitme_up.user.booking.BookingHistory_Tab1Fragment
import com.example.fitme_up.user.booking.BookingHistory_Tab2Fragment

class BookingHistoryPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val titles = arrayOf("Nearby Venue", "Available Venue")

    override fun getItemCount(): Int {
        return 2 // Return the number of fragments in the ViewPager2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BookingHistory_Tab1Fragment() // Return the Fragment1 instance
            1 -> BookingHistory_Tab2Fragment() // Return the Fragment2 instance
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

}