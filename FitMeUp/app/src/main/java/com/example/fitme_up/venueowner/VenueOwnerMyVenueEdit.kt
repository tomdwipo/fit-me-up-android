package com.example.fitme_up.venueowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.dataset.VenueImageData

class VenueOwnerMyVenueEdit : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var saveVenueBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_my_venue_edit, container, false)

        viewPager = view.findViewById(R.id.venuePager)
        saveVenueBtn = view.findViewById(R.id.btn_venue_edit_save)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        viewPager.adapter = VenueImageAdapter(imageList)


        val alertDialog = getActivity()?.let {

            AlertDialog.Builder(it)
                .setTitle("Confirm Save")
                .setMessage("Are you sure you want to save this venue?")
                .setPositiveButton("Yes") { _, _ ->
                    fragmentManager?.popBackStack()
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        saveVenueBtn.setOnClickListener {
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