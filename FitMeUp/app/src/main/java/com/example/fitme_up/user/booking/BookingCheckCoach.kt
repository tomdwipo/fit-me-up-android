package com.example.fitme_up.user.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class BookingCheckCoach : Fragment() {

    lateinit var findCoachBtn: Button
    lateinit var findVenueBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_check_coach, container, false)

        findCoachBtn = view.findViewById(R.id.findCoachBtn)
        findVenueBtn = view.findViewById(R.id.findVenueBtn)

        findCoachBtn.setOnClickListener() {
            val frag = BookingCoachFind()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.fragment_cont, frag)?.commit()
            tran?.addToBackStack(null)
        }



        findVenueBtn.setOnClickListener() {
            val frag = BookingVenueFind()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.fragment_cont, frag)?.commit()
            tran?.addToBackStack(null)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Book a Venue"

        return view
    }
}