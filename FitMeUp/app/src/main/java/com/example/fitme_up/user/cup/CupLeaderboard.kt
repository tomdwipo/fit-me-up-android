package com.example.fitme_up.user.cup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.cupAdapter.CupLeaderboardAdapter
import com.example.fitme_up.user.dataset.CupMatchData

class CupLeaderboard : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_leaderboard, container, false)

        recyclerView = view.findViewById(R.id.recycler_cup_match_details)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = arrayListOf(
            CupMatchData("Schauffele", 2, "Mcilroy", 3),
            CupMatchData("Cantlay", 2, "Lowry", 0),
            CupMatchData("Scheffler", 0, "Rahm", 1),
            CupMatchData("Dechambeau", 3, "Garcia", 2),
            CupMatchData("Morikawa", 1, "Hovland", 0),
            CupMatchData("Johnson", 4, "Casey", 2)
        )

        viewAdapter = CupLeaderboardAdapter(dataList2, this)

        recyclerView.adapter = viewAdapter


    }
}