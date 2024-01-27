package com.example.fitme_up.venueowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.dataset.VenueImageData

class VenueOwnerMyVenueDetails : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var editVenueBtn: Button

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView
    private lateinit var venueDescText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_my_venue_details, container, false)

        viewPager = view.findViewById(R.id.venuePager)
        editVenueBtn = view.findViewById(R.id.btn_edit_venue)

        venueNameText = view.findViewById(R.id.text_venue_name)
        venueSportText = view.findViewById(R.id.text_venue_sport)
        venueDomicileText = view.findViewById(R.id.text_venue_domicile)
        venueDescText = view.findViewById(R.id.text_venue_desc)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        venueNameText.text = "Grand Sports Center Kuningan"
        venueSportText.text = "Futsal"
        venueDomicileText.text = "Jakarta Selatan"
        venueDescText.text = "Grand Sports Center Kuningan Futsal Venue with many facilities\n" +
                "- Food Court\n" +
                "- Parking Lot\n" +
                "- Toilet"

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        editVenueBtn.setOnClickListener() {
            val frag = VenueOwnerMyVenueEdit()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

        viewPager.adapter = VenueImageAdapter(imageList)



        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = activity?.supportFragmentManager?.backStackEntryCount
            if (backStackCount != null) {
                if (backStackCount > 0) {

//                    Log.d("print", fragmentManager.findFragmentByTag("lfg_find_venue").toString())
//                    val fm = fragmentManager.findFragmentByTag("lfg_find_venue")

//                    if (fm != null) {
//                        addFragmentWithTag(this, "lfg_venue_details")
//                        Log.d("print", "from lfg find venue")
//                        editVenueBtn.text = "Book Venue for LFG"
//                        Toast.makeText(activity, "from lfg", Toast.LENGTH_SHORT).show()
                        //tambah logic data kalo dari lfg (masukin ke lfg DB)
//                    }
                }
            }
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

}