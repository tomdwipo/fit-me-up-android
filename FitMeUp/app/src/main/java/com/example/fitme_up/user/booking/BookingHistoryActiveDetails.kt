package com.example.fitme_up.user.booking

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.dataset.VenueImageData

class BookingHistoryActiveDetails : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var viewCoachBtn: Button

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView
    private lateinit var venueDescText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_history_active_details, container, false)

        viewPager = view.findViewById(R.id.venuePager)
        viewCoachBtn = view.findViewById(R.id.btn_view_coach_details)

        venueNameText = view.findViewById(R.id.text_venue_name)
        venueSportText = view.findViewById(R.id.text_venue_sport)
        venueDomicileText = view.findViewById(R.id.text_venue_domicile)
        venueDescText = view.findViewById(R.id.text_venue_desc)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        venueNameText.text = "Tifosi"
        venueSportText.text = "Badminton"
        venueDomicileText.text = "Jakarta Timur"
        venueDescText.text = "Tifosi Badminton Venue with many facilities\n" +
                "- Food Court\n" +
                "- Parking Lot\n" +
                "- Toilet"

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        viewCoachBtn.setOnClickListener(){
            val frag = BookingHistoryCoachDetails()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.fragment_cont, frag)?.commit()
            tran?.addToBackStack(null)
        }
//        kasih logic ada coach apa kaga
//        kyk booking ini ada coach id(?)
//        kalo ngga ada tambah -> viewCoachBtn.visibility = View.GONE

        viewPager.adapter = VenueImageAdapter(imageList)
        venueDescText.movementMethod = ScrollingMovementMethod()

    }

}