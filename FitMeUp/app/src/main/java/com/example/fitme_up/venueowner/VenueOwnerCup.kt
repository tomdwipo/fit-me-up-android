package com.example.fitme_up.venueowner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.venueowner.adapter.VenueOwnerCupListAdapter
import com.example.fitme_up.venueowner.dataset.VenueOwnerCupData

class VenueOwnerCup : Fragment() {

    private lateinit var createCupBtn: Button

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_cup, container, false)

        createCupBtn = view.findViewById(R.id.btn_venue_cup_create_new)

        recyclerView = view.findViewById(R.id.recycler_venue_myvenue)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = listOf(
            VenueOwnerCupData("Champion Cup", "Futsal", "Grand Sports Center Kuningan", "15 Januari 10:00"),
            VenueOwnerCupData("Friendly Cup", "Futsal", "Grand Sports Center Kuningan", "20 Januari 12:00"),
            VenueOwnerCupData("Champions League", "Badminton", "Supreme Arena Badminton", "26 Januari 09:00")
        )

        viewAdapter = VenueOwnerCupListAdapter(dataList, this)
        recyclerView.adapter = viewAdapter

        createCupBtn.setOnClickListener(){
            val frag = VenueOwnerCupCreateNew()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }
    }

}