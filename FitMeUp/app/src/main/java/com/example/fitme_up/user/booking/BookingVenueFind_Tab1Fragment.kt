package com.example.fitme_up.user.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.bookingAdapter.BookingVenueTab1Adapter
import com.example.fitme_up.user.dataset.VenueData

class BookingVenueFind_Tab1Fragment : Fragment(), OnButtonClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_find_tab1, container, false)

        recyclerView = view.findViewById(R.id.venueRecycleListTab1)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            VenueData("Tifosi", "Badminton", "Jakarta Timur", 50000),
            VenueData("Zy Futsal", "Badminton", "Jakarta Timur", 150000),
            VenueData("Top Sport Center - Duren Sawit", "Futsal", "Jakarta Timur", 75000),
            VenueData("GOR BFM Pulogebang", "Badminton", "Jakarta Timur", 75000)
        )

        viewAdapter = BookingVenueTab1Adapter(dataList2, this)

        recyclerView.adapter = viewAdapter

//        val fragmentManager = activity?.supportFragmentManager
//        if (fragmentManager != null) {
//            val backStackCount = activity?.supportFragmentManager?.backStackEntryCount
//            if (backStackCount != null) {
//                if (backStackCount > 0) {
//                    Toast.makeText(activity, "from lfg book venue", Toast.LENGTH_SHORT).show()
//
//                    if (parentFragmentManager.findFragmentByTag("lfg_book_venue") != null) {
//
//                    }
//                }
//            }
//        }
        
    }

    override fun onButtonClick() {
        val frag = BookingVenueDetails()
        val tran = fragmentManager?.beginTransaction()
        tran?.addToBackStack(null)
        tran?.replace(R.id.fragment_cont, frag)?.commit()
    }

}