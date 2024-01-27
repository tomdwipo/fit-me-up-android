package com.example.fitme_up.user.booking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.fitme_up.R

class BookingVenueOrder : Fragment() {

    private lateinit var venueOrderBtn: Button
    private lateinit var venueDateSpin: Spinner
    private lateinit var venueTimeSpin: Spinner

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_order, container, false)

        venueOrderBtn = view.findViewById(R.id.btn_confirm_venue)
        venueDateSpin = view.findViewById(R.id.spin_venue_book_date)
        venueTimeSpin = view.findViewById(R.id.spin_venue_book_time)

        venueNameText = view.findViewById(R.id.text_venue_name)
        venueDomicileText = view.findViewById(R.id.text_venue_domicile)
        venueSportText = view.findViewById(R.id.text_venue_sport)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        venueNameText.text = "Tifosi"
        venueSportText.text = "Badminton"
        venueDomicileText.text = "Jakarta Timur"

        val coachingDay = resources.getStringArray(R.array.coaching_day)
        val coachingTime = resources.getStringArray(R.array.venue_time)

        if (venueDateSpin != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, coachingDay
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            venueDateSpin.adapter = adapter
        }

        if (venueTimeSpin != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, coachingTime
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            venueTimeSpin.adapter = adapter
        }

        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = fragmentManager.backStackEntryCount
            if (backStackCount > 0) {

                val fm = fragmentManager.findFragmentByTag("lfg_venue_details")

                if (fm != null) {
                    addFragmentWithTag(this, "lfg_venue_order")
                    Log.d("print", "from lfg venue details")
                    venueOrderBtn.text = "Book Venue for LFG"

                    venueDateSpin.visibility = View.GONE
                    //tambah logic data kalo dari lfg (masukin ke lfg DB)
                }
            }
        }

        venueOrderBtn.setOnClickListener() {
            val frag = BookingVenueConfirm()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}