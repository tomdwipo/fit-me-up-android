package com.example.fitme_up.user.adapter.settingsAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitme_up.user.settings.SettingsTransactionTab1Fragment
import com.example.fitme_up.user.settings.SettingsTransactionTab2Fragment

class SettingsTransactionAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val titles = arrayOf("Nearby LFG", "Available LFG")

    override fun getItemCount(): Int {
        return 2 // Return the number of fragments in the ViewPager2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SettingsTransactionTab1Fragment() // Return the Fragment1 instance
            1 -> SettingsTransactionTab2Fragment() // Return the Fragment2 instance
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}