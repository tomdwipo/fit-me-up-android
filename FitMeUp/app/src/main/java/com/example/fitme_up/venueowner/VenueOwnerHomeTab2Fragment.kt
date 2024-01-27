package com.example.fitme_up.venueowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.venueowner.adapter.VenueOwnerHomeTab2Adapter
import com.example.fitme_up.venueowner.dataset.VenueOwnerBookData

class VenueOwnerHomeTab2Fragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_home_tab2, container, false)

        recyclerView = view.findViewById(R.id.recycler_venue_home_tab3)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = listOf(
            VenueOwnerBookData("Ciputat One Futsal", "#123124575338123","Futsal", "On Progress"),
            VenueOwnerBookData("Futsal & Badminton Arena 68", "#123124354538123","Futsal", "On Progress"),
            VenueOwnerBookData("Champion Futsal", "#123124237538123", "Futsal","On Progress")
        )

        viewAdapter = VenueOwnerHomeTab2Adapter(dataList, this)
        recyclerView.adapter = viewAdapter

    }
}