package com.example.fitme_up.venueowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnItemClickListener
import com.example.fitme_up.R
import com.example.fitme_up.venueowner.adapter.VenueOwnerMyVenueAdapter

class VenueOwnerMyVenue : Fragment(), OnItemClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var addVenueBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_my_venue, container, false)

        recyclerView = view.findViewById(R.id.recycler_venue_myvenue)

        addVenueBtn = view.findViewById(R.id.btn_venue_add_new_venue)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val venueList = listOf(//ganti logicny buat ngambil data dari DB buat isi venue
            "Grand Sports Center Kuningan",
            "Ciputat One Futsal",
            "Supreme Arena Badminton"
        )

        viewAdapter = VenueOwnerMyVenueAdapter(venueList,this)
        recyclerView.adapter = viewAdapter

        addVenueBtn.setOnClickListener(){
            val frag = VenueOwnerMyVenueAdd()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

    }

    override fun onItemClick(position: Int) {
        val fragment: Fragment = when (position) {
            0 -> VenueOwnerMyVenueDetails()
            else -> VenueOwnerMyVenueDetails()
        }

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}