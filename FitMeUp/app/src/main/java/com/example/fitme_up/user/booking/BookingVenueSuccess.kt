package com.example.fitme_up.user.booking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.fitme_up.R
import com.example.fitme_up.user.lfg.LfgDetails

class BookingVenueSuccess : Fragment() {

    private lateinit var returnHomeBtn: Button
    private lateinit var successTextView: TextView

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_success, container, false)

        returnHomeBtn = view.findViewById(R.id.returnHomeBtn)
        successTextView = view.findViewById(R.id.text_book_success)

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

        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = fragmentManager.backStackEntryCount
            if (backStackCount > 0) {

                val fm = fragmentManager.findFragmentByTag("lfg_venue_order")

                if (fm != null) {
                    addFragmentWithTag(this, "lfg_venue_success")
                    Log.d("print", "from lfg venue confirm")
                    returnHomeBtn.text = "Return to my LFG"
                    successTextView.text = "LFG Venue Booking Success"

                    returnHomeBtn.setOnClickListener() {
                        val frag = LfgDetails()
                        val tran = fragmentManager?.beginTransaction()
                        tran?.addToBackStack(null)
                        tran?.replace(R.id.fragment_cont, frag)?.commit()
                    }
//                    Toast.makeText(activity, "prev fragment lfg details", Toast.LENGTH_SHORT).show()
                    //tambah logic data kalo dari lfg (masukin ke lfg DB)
                }
                else{
                    returnHomeBtn.setOnClickListener() {
                        fragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                }
            }
        }
    }

    private fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}