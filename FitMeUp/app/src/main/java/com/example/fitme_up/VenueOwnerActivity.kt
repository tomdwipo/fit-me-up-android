package com.example.fitme_up

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fitme_up.venueowner.VenueOwnerBookingHistory
import com.example.fitme_up.venueowner.VenueOwnerCup
import com.example.fitme_up.venueowner.VenueOwnerHome
import com.example.fitme_up.venueowner.VenueOwnerMyVenue
import com.example.fitme_up.venueowner.VenueOwnerWithdraw
import com.google.android.material.navigation.NavigationView

class VenueOwnerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbarTitleText: TextView
    private val prefs by lazy {
        getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_venue_owner)
        setSupportActionBar(findViewById(R.id.toolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackgroundlight)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navView = findViewById<NavigationView>(R.id.nav_view_venue_owner)
        toolbarTitleText = findViewById(R.id.text_toolbar_title)


        drawerLayout = findViewById(R.id.drawer_layout)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_cont, VenueOwnerHome())
            .commit()
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

    }

    fun setOnBackPressedListener(onBackPressedCallback: OnBackPressedCallback) {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home_venue -> {
                toolbarTitleText.setText(getString(R.string.venue_home_title))
                Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                swapFragment(VenueOwnerHome(), null)
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

            R.id.nav_booking_history_venue -> {
                toolbarTitleText.setText(getString(R.string.venue_book_history_title))
                Toast.makeText(applicationContext, "Request History", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(VenueOwnerBookingHistory(), null)
//                if(supportFragmentManager.backStackEntryCount > 0)

            }

            R.id.nav_myvenue_venue -> {
                toolbarTitleText.setText(getString(R.string.venue_my_venue_title))
                Toast.makeText(applicationContext, "My Profile", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(VenueOwnerMyVenue(), "profile")
            }

            R.id.nav_cup_venue -> {
                toolbarTitleText.setText(getString(R.string.venue_cup_title))
                Toast.makeText(applicationContext, "Withdraw Balance", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(VenueOwnerCup(), null)
            }

            R.id.nav_withdraw_venue -> {
                toolbarTitleText.setText(getString(R.string.venue_withdraw_title))
                Toast.makeText(applicationContext, "Withdraw Balance", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(VenueOwnerWithdraw(), null)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_cont)

        if (currentFragment is VenueOwnerBookingHistory && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (currentFragment is VenueOwnerMyVenue && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (currentFragment is VenueOwnerCup && supportFragmentManager.backStackEntryCount > 0) {
            val previousFragmentBackStackEntry = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val prevFragmentStack = previousFragmentBackStackEntry.name

            if (prevFragmentStack == null) {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } else {
//                Toast.makeText(applicationContext, "from lfg", Toast.LENGTH_SHORT).show()
                //tambah logic kl dari lfg
            }

        }

        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {

            // Handle open close drawer
            if (currentFragment is VenueOwnerHome) {
                val builder = AlertDialog.Builder(this)
                builder.setCancelable(false)
                builder.setMessage("Are you sure you want to quit?")
                builder.setPositiveButton("Quit") { _, _ ->
                    super.onBackPressed()
                }
                builder.setNegativeButton("Cancel") { _, _ -> }
                val alert = builder.create()
                alert.show()
            } else {
                supportFragmentManager.popBackStack()
            }
        } else {
            // Handle closing drawer
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun swapFragment(frag: Fragment, tag: String?) {
        val fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.fragment_cont, frag, tag)
        fr.addToBackStack(null)
        fr.commit()
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

//    fun getPreviousFragmentName(fragment: Fragment): String? {
//        val fragmentManager = fragment.requireActivity().supportFragmentManager
//        val backStackCount = fragmentManager.backStackEntryCount
//
//        // If there is at least 2 fragments in the back stack, we can get the previous fragment name
//        if (backStackCount >= 2) {
//            val backStackFragment = fragmentManager.getBackStackEntryAt(backStackCount - 2)
//            return backStackFragment.name
//        }
//        // If there is only one fragment in the back stack, there is no previous fragment
//        return null
//    }
}