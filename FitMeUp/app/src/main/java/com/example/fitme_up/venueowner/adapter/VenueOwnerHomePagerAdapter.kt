package com.example.fitme_up.venueowner.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitme_up.venueowner.VenueOwnerHomeTab1Fragment
import com.example.fitme_up.venueowner.VenueOwnerHomeTab2Fragment

class VenueOwnerHomePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VenueOwnerHomeTab1Fragment()
            1 -> VenueOwnerHomeTab2Fragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}