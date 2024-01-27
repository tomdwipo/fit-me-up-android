package com.example.fitme_up.user.lfg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.lfgAdapter.LfgPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LfgFind : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var createLfgBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lfg_find, container, false)

        viewPager = view.findViewById(R.id.pager_lfg_find)
        tabLayout = view.findViewById(R.id.tab_lfg_find)
        createLfgBtn = view.findViewById(R.id.btn_lfg_create_new)

        viewPager.adapter = LfgPagerAdapter(requireActivity())
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
                0 -> tab.text = "Nearby LFG"
                1 -> tab.text = "Available LFG"
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

        //buat logic kalo udh buat lfg buttonnya berubah jadi View My LFG
        //pas diklik ke page LfgDetails()
        createLfgBtn.setOnClickListener(){
            val frag = LfgCreateNew()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

    }

    fun getPreviousFragmentName(fragment: Fragment): String? {
        val fragmentManager = fragment.requireActivity().supportFragmentManager
        val backStackCount = fragmentManager.backStackEntryCount

        // If there is at least 2 fragments in the back stack, we can get the previous fragment name
        if (backStackCount >= 2) {
            val backStackFragment = fragmentManager.getBackStackEntryAt(backStackCount - 2)
            return backStackFragment.name
        }
        // If there is only one fragment in the back stack, there is no previous fragment
        return null
    }
}