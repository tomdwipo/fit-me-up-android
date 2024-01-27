package com.example.fitme_up.venueowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class VenueOwnerCupCreateNew : Fragment() {

    private lateinit var createCupBtn: Button
    private lateinit var venueSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_cup_create_new, container, false)

        createCupBtn = view.findViewById(R.id.btn_venue_cup_create)
        venueSpinner = view.findViewById(R.id.spinner_venue_owner_cup_venue)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val venue_list = resources.getStringArray(R.array.venue_list)
        if (venueSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, venue_list
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            venueSpinner.adapter = adapter
        }

        val alertDialog = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Join LFG")
                .setMessage("Are you sure you want to join this LFG?")
                .setPositiveButton("Join LFG") { _, _ ->
                    //kasih logic dari DB buat get data player

                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        createCupBtn.setOnClickListener(){
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

}