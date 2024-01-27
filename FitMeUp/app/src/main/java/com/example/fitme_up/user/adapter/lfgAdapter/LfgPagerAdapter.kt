package com.example.fitme_up.user.adapter.lfgAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitme_up.user.lfg.LfgTab1Fragment
import com.example.fitme_up.user.lfg.LfgTab2Fragment

class LfgPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val titles = arrayOf("Nearby LFG", "Available LFG")

    override fun getItemCount(): Int {
        return 2 // Return the number of fragments in the ViewPager2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LfgTab1Fragment() // Return the Fragment1 instance
            1 -> LfgTab2Fragment() // Return the Fragment2 instance
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}