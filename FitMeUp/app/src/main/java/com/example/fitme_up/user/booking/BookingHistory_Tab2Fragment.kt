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
import com.example.fitme_up.user.adapter.bookingHistoryAdapter.BookingHistoryTab2Adapter
import com.example.fitme_up.user.dataset.HistoryData

class BookingHistory_Tab2Fragment : Fragment(), OnButtonClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_history_tab2, container, false)

        recyclerView = view.findViewById(R.id.historyRecycleListTab2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            HistoryData("Tifosi", "Badminton", "Coach 1", "Jakarta Timur", "17 December 2023"),
            HistoryData("Zy Futsal", "Badminton", "", "Jakarta Timur", "16 December 2023"),
            HistoryData("Top Sport Center - Duren Sawit", "Futsal", "Coach 1", "Jakarta Timur", "15 December 2023")
        )

        viewAdapter = BookingHistoryTab2Adapter(dataList2, this)

        recyclerView.adapter = viewAdapter
    }

    override fun onButtonClick() {
        val frag = BookingHistoryDetails()
        val tran = fragmentManager?.beginTransaction()
        tran?.replace(R.id.fragment_cont, frag)?.commit()
        tran?.addToBackStack(null)
    }

}