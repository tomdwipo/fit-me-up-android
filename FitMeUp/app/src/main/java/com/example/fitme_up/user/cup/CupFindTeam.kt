package com.example.fitme_up.user.cup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelFragmentTag
import com.example.fitme_up.user.adapter.cupAdapter.CupTeamAdapter
import com.example.fitme_up.user.dataset.CupTeamData

class CupFindTeam : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var cupDomicileText: TextView

    private lateinit var sharedViewModel: ViewModelFragmentTag

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_find_team, container, false)

        recyclerView = view.findViewById(R.id.recycler_cup_team_list)

        cupDomicileText = view.findViewById(R.id.text_cup_list_domicile)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelFragmentTag::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cupDomicileText.text = "Available Team in Jakarta Timur"

        sharedViewModel.updateSelectedState("find_team")

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            CupTeamData("Team 1", "Futsal", "Jakarta Timur", 3),
            CupTeamData("Team 2", "Badminton", "Jakarta Timur", 3),
            CupTeamData("Team 3", "Futsal", "Jakarta Timur", 4),
            CupTeamData("Team 4", "Futsal", "Jakarta Timur", 6),
            CupTeamData("Team 5", "Badminton", "Jakarta Timur", 8),
            CupTeamData("Team 6", "Futsal", "Jakarta Timur", 2)
        )

        viewAdapter = CupTeamAdapter(dataList2, this)

        recyclerView.adapter = viewAdapter



    }

    fun addFragmentWithTag(fragment: Fragment, tag: String?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

}