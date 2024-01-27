package com.example.fitme_up.user.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.fitme_up.R

class BookingCoachDetails : Fragment() {

    private lateinit var coachNameText: TextView
    private lateinit var coachPhoneText: TextView
    private lateinit var coachDomicileText: TextView
    private lateinit var coachSportText: TextView

    private lateinit var bookCoachBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_coach_details, container, false)

        coachNameText = view.findViewById(R.id.text_coach_name)
        coachPhoneText = view.findViewById(R.id.text_coach_phone)
        coachDomicileText = view.findViewById(R.id.text_coach_domicile)
        coachSportText = view.findViewById(R.id.text_coach_sport)

        bookCoachBtn = view.findViewById(R.id.bookCoachBtn)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coachNameText.text = "Coach 1"
        coachDomicileText.text = "Jakarta Selatan"
        coachPhoneText.text = "081122334455"
        coachSportText.text = "Badminton"

        bookCoachBtn.setOnClickListener() {
            val frag = BookingCoachOrder()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.fragment_cont, frag)?.commit()
            tran?.addToBackStack(null)
        }

    }

}