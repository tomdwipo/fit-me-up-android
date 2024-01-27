package com.example.fitme_up.coach.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitme_up.coach.CoachHomeTab1Fragment
import com.example.fitme_up.coach.CoachHomeTab2Fragment

class CoachHomePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int {
        return 2 // Return the number of fragments in the ViewPager2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CoachHomeTab1Fragment() // Return the Fragment1 instance
            1 -> CoachHomeTab2Fragment() // Return the Fragment2 instance
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}