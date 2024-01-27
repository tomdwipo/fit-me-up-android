package com.example.fitme_up.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.dataset.VenueImageData

class CoachRequestHistoryDetails : Fragment() {

    private lateinit var viewPager: ViewPager2

    private lateinit var textVenueName: TextView
    private lateinit var textVenueSport: TextView
    private lateinit var textVenueDomicile: TextView
    private lateinit var textVenueDesc: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_request_history_details, container, false)

        viewPager = view.findViewById(R.id.venuePager)

        textVenueName = view.findViewById(R.id.text_venue_name)
        textVenueSport = view.findViewById(R.id.text_venue_sport)
        textVenueDomicile = view.findViewById(R.id.text_venue_domicile)
        textVenueDesc = view.findViewById(R.id.text_venue_desc)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textVenueName.text = "Ciputat One Futsal"
        textVenueSport.text = "Futsal"
        textVenueDomicile.text = "Jakarta Selatan"
        textVenueDesc.text = "Ciputat One Futsal venue with many facilities\n" +
                "- Food Court\n" +
                "- Parking Lot\n" +
                "- Toilet"

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        viewPager.adapter = VenueImageAdapter(imageList)
    }

}