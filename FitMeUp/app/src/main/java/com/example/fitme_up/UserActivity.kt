package com.example.fitme_up

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fitme_up.user.HomeUserFragment
import com.example.fitme_up.user.booking.BookingCheckCoach
import com.example.fitme_up.user.booking.BookingCoachSuccess
import com.example.fitme_up.user.booking.BookingHistory
import com.example.fitme_up.user.booking.BookingVenueFind
import com.example.fitme_up.user.booking.BookingVenueSuccess
import com.example.fitme_up.user.cup.CupList
import com.example.fitme_up.user.cup.CupTeamDetails
import com.example.fitme_up.user.lfg.LfgDetails
import com.example.fitme_up.user.lfg.LfgFind
import com.example.fitme_up.user.settings.SettingsList
import com.google.android.material.navigation.NavigationView


class UserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbarTitleText: TextView

    private val prefs by lazy {
        getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackgroundlight)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        toolbarTitleText = findViewById(R.id.text_toolbar_title)

        drawerLayout = findViewById(R.id.drawer_layout)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_cont, HomeUserFragment())
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


//        val previousFragmentName = supportFragmentManager.findFragmentById(R.id.fragment_cont)
//            ?.let { getPreviousFragmentName(it) }
//        val fragmentManager = getFragmentManager()
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_cont)
//        if (currentFragment is LfgCreateNew && supportFragmentManager.backStackEntryCount > 0) {
//            if (previousFragmentName == "lfg_create") {
//                supportFragmentManager.popBackStack("lfg_create", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//
//            }else Toast.makeText(applicationContext, "LfgCreateNew", Toast.LENGTH_SHORT).show()
//        }

        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                toolbarTitleText.setText("Home")//ganti logic biar pake nama user "Hi, [username]"
//                Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                swapFragment(HomeUserFragment())
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

            R.id.nav_book -> {
                toolbarTitleText.setText(getString(R.string.user_book_title))
//                Toast.makeText(applicationContext, "Book a Venue", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(BookingCheckCoach())

            }

            R.id.nav_myBook -> {
                toolbarTitleText.setText(getString(R.string.user_mybook_title))
//                Toast.makeText(applicationContext, "My Booking", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(BookingHistory())
            }

            R.id.nav_LFG -> {
                toolbarTitleText.setText(getString(R.string.user_lfg_title))
//                Toast.makeText(applicationContext, "LFG", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(LfgFind())
            }

            R.id.nav_cup -> {
                toolbarTitleText.setText(getString(R.string.user_cup_title))
//                Toast.makeText(applicationContext, "Cup", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(CupList())
            }

            R.id.nav_settings -> {
                toolbarTitleText.setText(getString(R.string.user_setting_title))
//                Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(SettingsList())
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_cont)

        if (currentFragment is BookingVenueSuccess && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (currentFragment is BookingCoachSuccess && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (currentFragment is BookingVenueFind && supportFragmentManager.backStackEntryCount > 0) {
            val previousFragmentBackStackEntry = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val prevFragmentStack = previousFragmentBackStackEntry.name

            if (prevFragmentStack == null) {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } else {
//                Toast.makeText(applicationContext, "from lfg", Toast.LENGTH_SHORT).show()
                //tambah logic kl dari lfg
            }

        }
        if (currentFragment is LfgDetails && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack("lfg_create", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (currentFragment is CupTeamDetails && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack("create_team", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {

            // Handle open close drawer
            if (currentFragment is HomeUserFragment) {
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

    fun swapFragment(frag: Fragment) {
        val fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.fragment_cont, frag)
        fr.addToBackStack(null)
        fr.commit()
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String?) {
        supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}