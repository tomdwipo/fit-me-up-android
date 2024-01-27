package com.example.fitme_up.user.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.fitme_up.R

class BookingCoachOrder : Fragment() {

    private lateinit var orderCoachBtn: Button
    private lateinit var coachDaySpinner: Spinner
    private lateinit var coachTimeSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_coach_order, container, false)

        orderCoachBtn = view.findViewById(R.id.orderCoachBtn)
        coachDaySpinner = view.findViewById(R.id.spinner_venue_book_date)
        coachTimeSpinner = view.findViewById(R.id.spinner_venue_book_time)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coachingDay = resources.getStringArray(R.array.coaching_day)
        val coachingTime = resources.getStringArray(R.array.coaching_time)

        if (coachDaySpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, coachingDay
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coachDaySpinner.adapter = adapter
        }

        if (coachTimeSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, coachingTime
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coachTimeSpinner.adapter = adapter
        }

        orderCoachBtn.setOnClickListener() {
            val frag = BookingCoachConfirm()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.fragment_cont, frag)?.commit()
            tran?.addToBackStack(null)
        }

    }

}