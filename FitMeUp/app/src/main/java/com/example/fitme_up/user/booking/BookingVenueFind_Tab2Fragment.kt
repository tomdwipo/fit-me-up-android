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
import com.example.fitme_up.user.adapter.bookingAdapter.BookingVenueTab2Adapter
import com.example.fitme_up.user.dataset.VenueData

class BookingVenueFind_Tab2Fragment : Fragment(), OnButtonClickListener {

    private lateinit var viewAdapter2: RecyclerView.Adapter<*>
    private lateinit var viewManager2: RecyclerView.LayoutManager
    private lateinit var recyclerView2: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_find_tab2, container, false)

        recyclerView2 = view.findViewById(R.id.venueRecycleListTab2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager2 = LinearLayoutManager(context)
        recyclerView2.layoutManager = viewManager2

        val dataList2 = listOf(
            VenueData("Grand Sports Center Kuningan", "Badminton", "Jakarta Selatan", 150000),
            VenueData("Cometa Arena", "Badminton", "Jakarta Utara", 200000),
            VenueData("ASABA Green Area", "Futsal", "Jakarta Barat", 100000)
        )

        viewAdapter2 = BookingVenueTab2Adapter(dataList2, this)

        recyclerView2.adapter = viewAdapter2
    }

    override fun onButtonClick() {
        val frag = BookingVenueDetails()
        val tran = fragmentManager?.beginTransaction()
        tran?.replace(R.id.fragment_cont, frag)?.commit()
        tran?.addToBackStack(null)
    }
}