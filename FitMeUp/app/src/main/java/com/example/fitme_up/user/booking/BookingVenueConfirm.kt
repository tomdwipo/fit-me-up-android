package com.example.fitme_up.user.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class BookingVenueConfirm : Fragment() {

    private lateinit var confirmVenueBtn: Button

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_confirm, container, false)

        confirmVenueBtn = view.findViewById(R.id.btn_confirm_venue_book)

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
                    addFragmentWithTag(this, "lfg_venue_confirm")
                    Log.d("print", "from lfg venue order")
                    confirmVenueBtn.text = "Confirm Book Venue for LFG"
//                    Toast.makeText(activity, "prev fragment lfg details", Toast.LENGTH_SHORT).show()
                    //tambah logic data kalo dari lfg (masukin ke lfg DB)
                }
            }
        }

        val alertDialog = getActivity()?.let {
            AlertDialog.Builder(it)
                .setTitle("Confirm Book")
                .setMessage("Are you sure you want to book this venue?")
                .setPositiveButton("OK") { _, _ ->
                    val frag = BookingVenueSuccess()
                    val tran = fragmentManager?.beginTransaction()
                    tran?.addToBackStack(null)
                    tran?.replace(R.id.fragment_cont, frag)?.commit()

                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        confirmVenueBtn.setOnClickListener {
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }
    }

    private fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }
}