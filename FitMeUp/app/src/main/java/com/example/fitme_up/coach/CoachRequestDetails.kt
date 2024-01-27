package com.example.fitme_up.coach

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelFragmentTag
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.dataset.VenueImageData

class CoachRequestDetails : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnAccept: Button
    private lateinit var btnDecline: Button
    private lateinit var btnLayout: ConstraintLayout
    private lateinit var textStatus: TextView
    private lateinit var sharedViewModel: ViewModelFragmentTag

    private lateinit var textVenueName: TextView
    private lateinit var textVenueSport: TextView
    private lateinit var textVenueDomicile: TextView
    private lateinit var textVenueDesc: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_request_details, container, false)

        viewPager = view.findViewById(R.id.venuePager)
        btnAccept= view.findViewById(R.id.btn_coach_book_accept)
        btnDecline= view.findViewById(R.id.btn_coach_book_decline)
        btnLayout = view.findViewById(R.id.constraintLayout)
        textStatus = view.findViewById(R.id.text_coach_book_status)

        textVenueName = view.findViewById(R.id.text_venue_name)
        textVenueSport = view.findViewById(R.id.text_venue_sport)
        textVenueDomicile = view.findViewById(R.id.text_venue_domicile)
        textVenueDesc = view.findViewById(R.id.text_venue_desc)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelFragmentTag::class.java)

        sharedViewModel.selectedState.observe(viewLifecycleOwner, Observer { selected ->
            if (selected != null) {
                //ini buat get sport di page sebelumnya
                when (selected) {
                    "Tab 2" -> {
                        Log.d("print", "$selected")
                        btnLayout.visibility = View.GONE
                        textStatus.text = "Accepted"
                    }
                }
            }
            else{
                btnLayout.visibility = View.VISIBLE
            }
        })

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

        val alertDialog = getActivity()?.let {

            AlertDialog.Builder(it)
                .setTitle("Accept Request")
                .setMessage("Are you sure you want to accept this booking request?")
                .setPositiveButton("Yes") { _, _ ->
                    addFragmentWithTag(this, "request_accepted")
                    textStatus.text = "Accepted"
                    btnLayout.visibility = View.GONE
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        btnAccept.setOnClickListener {
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }

        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = activity?.supportFragmentManager?.backStackEntryCount
            if (backStackCount != null) {
                if (backStackCount > 0) {

                    val fm = fragmentManager.findFragmentByTag("coach_history")

                    if (fm != null) {
                        btnLayout.visibility = View.GONE
                        //tambah logic kalo status udah accept
                    }
                    else{
                        btnLayout.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}