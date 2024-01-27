package com.example.fitme_up.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.coach.adapter.CoachHomeTab1Adapter
import com.example.fitme_up.coach.dataset.CoachingData

class CoachHomeTab1Fragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_home_tab1, container, false)

        recyclerView = view.findViewById(R.id.recycler_coach_home_tab1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = listOf(
            CoachingData("Ciputat One Futsal", "Jakarta Selatan", "Futsal", "10 December 09:00", 2),
            CoachingData("The Planet Futsal", "Jakarta Utara", "Futsal", "13 December 16:00", 2),
            CoachingData("Grand Sports Center Kuningan", "Jakarta Selatan", "Futsal", "15 December 08:00", 1)
        )

        viewAdapter = CoachHomeTab1Adapter(dataList, this)
        recyclerView.adapter = viewAdapter

    }
}