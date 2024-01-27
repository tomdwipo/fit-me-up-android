package com.example.fitme_up.user.cup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.cupAdapter.CupTeamPlayerAdapter
import com.example.fitme_up.user.dataset.CupTeamMemberData

class CupLeaderboardTeamMemberList : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    var dataList: ArrayList<CupTeamMemberData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_leaderboard_team_member_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_cup_team_member)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        dataList = arrayListOf(
            CupTeamMemberData("John Rico", "Room Master"),
            CupTeamMemberData("Jon Rico", "Player"),
            CupTeamMemberData("Jhon Rico", "Player"),
            CupTeamMemberData("Juan Rico", "Player"),
            CupTeamMemberData("Joe Rico", "Player"),
            CupTeamMemberData("Jo Rico", "Player"),
            CupTeamMemberData("Jojo Rico", "Player")
        )

        viewAdapter = CupTeamPlayerAdapter(dataList)

        recyclerView.adapter = viewAdapter
    }

}