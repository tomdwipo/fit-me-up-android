package com.example.fitme_up.user.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.fitme_up.R

class BookingCoachSuccess : Fragment() {

    private lateinit var continueBookBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_coach_success, container, false)

        continueBookBtn = view.findViewById(R.id.btn_continue_book)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continueBookBtn.setOnClickListener() {
            val frag = BookingVenueFind()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.fragment_cont, frag)?.commit()
            tran?.addToBackStack(null)
        }
    }

}