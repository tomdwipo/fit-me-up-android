package com.example.fitme_up

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fitme_up.coach.CoachHome
import com.example.fitme_up.coach.CoachMyProfile
import com.example.fitme_up.coach.CoachRequestHistory
import com.example.fitme_up.coach.CoachWithdraw
import com.google.android.material.navigation.NavigationView

class CoachActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbarTitleText: TextView
    private val prefs by lazy {
        getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coach)
        setSupportActionBar(findViewById(R.id.toolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        getWindow().setBackgroundDrawableResource(R.drawable.assetbackgroundlight)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navView = findViewById<NavigationView>(R.id.nav_view_coach)
        toolbarTitleText = findViewById(R.id.text_toolbar_title)

        drawerLayout = findViewById(R.id.drawer_layout)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_cont, CoachHome())
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home_coach -> {
                toolbarTitleText.setText(getString(R.string.coach_home_title))
                Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                swapFragment(CoachHome(), null)
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

            R.id.nav_request_coach -> {
                toolbarTitleText.setText(getString(R.string.coach_request_title))
                Toast.makeText(applicationContext, "Request History", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(CoachRequestHistory(), null)

//                if(supportFragmentManager.backStackEntryCount > 0)

            }

            R.id.nav_profile_coach -> {
                toolbarTitleText.setText(getString(R.string.coach_my_profile_title))
                Toast.makeText(applicationContext, "My Profile", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(CoachMyProfile(), "profile")
            }

            R.id.nav_withdraw_coach -> {
                toolbarTitleText.setText(getString(R.string.coach_withdraw_title))
                Toast.makeText(applicationContext, "Withdraw Balance", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                swapFragment(CoachWithdraw(), null)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_cont)

        if (currentFragment is CoachMyProfile && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (currentFragment is CoachWithdraw && supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        if (currentFragment is CoachRequestHistory && supportFragmentManager.backStackEntryCount > 0) {
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
            if (currentFragment is CoachHome) {
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