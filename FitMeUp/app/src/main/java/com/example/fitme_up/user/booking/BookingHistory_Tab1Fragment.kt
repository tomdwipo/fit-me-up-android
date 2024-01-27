package com.example.fitme_up.user.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.bookingHistoryAdapter.BookingHistoryTab1Adapter
import com.example.fitme_up.user.dataset.BookingData
import com.example.fitme_up.user.dataset.VenueData

class BookingHistory_Tab1Fragment : Fragment(), OnButtonClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_history_tab1, container, false)

        recyclerView = view.findViewById(R.id.historyRecycleListTab1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = listOf(
            BookingData("Tifosi", "Badminton", "Coach 1", "Jakarta Timur", "28 December 2023"),
            BookingData("Zy Futsal", "Badminton", "", "Jakarta Timur", "24 December 2023"),
            BookingData("Top Sport Center - Duren Sawit", "Futsal", "Coach 1", "Jakarta Timur", "15 December 2023")
        )

        viewAdapter = BookingHistoryTab1Adapter(dataList, this)

        recyclerView.adapter = viewAdapter
    }

    override fun onButtonClick() {
        val frag = BookingHistoryActiveDetails()
        val tran = fragmentManager?.beginTransaction()
        tran?.replace(R.id.fragment_cont, frag)?.commit()
        tran?.addToBackStack(null)
    }
}

