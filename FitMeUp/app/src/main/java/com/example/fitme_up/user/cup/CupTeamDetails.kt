package com.example.fitme_up.user.cup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelFragmentTag
import com.example.fitme_up.user.adapter.cupAdapter.CupTeamPlayerAdapter
import com.example.fitme_up.user.dataset.CupTeamMemberData

class CupTeamDetails : Fragment() {

    private lateinit var editTeamBtn: Button
    private lateinit var findCupBtn: Button
    private lateinit var joinTeamBtn: Button
    private lateinit var leaveTeamBtn: Button
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var teamNameText: TextView
    private lateinit var teamDomicileText: TextView
    private lateinit var teamSportText: TextView
    private lateinit var teamMemberText: TextView

    var dataList: ArrayList<CupTeamMemberData> = ArrayList()

    private lateinit var sharedViewModel: ViewModelFragmentTag

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_team_details, container, false)

        editTeamBtn = view.findViewById(R.id.btn_cup_team_edit)
        findCupBtn = view.findViewById(R.id.btn_cup_find_cup)
        joinTeamBtn = view.findViewById(R.id.btn_cup_team_join)
        leaveTeamBtn = view.findViewById(R.id.btn_cup_team_leave)
        recyclerView = view.findViewById(R.id.recycler_team_player)

        teamNameText = view.findViewById(R.id.text_team_name)
        teamDomicileText = view.findViewById(R.id.text_team_domicile)
        teamSportText = view.findViewById(R.id.text_team_sports)

        teamMemberText = view.findViewById(R.id.text_team_num)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelFragmentTag::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamNameText.text = "Badminton Super Team"
        teamDomicileText.text = "Jakarta Timur"
        teamSportText.text = "Badminton"


        addFragmentWithTag(this, "cup_team_details")

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

        teamMemberText.text = dataList.size.toString() + " Player(s)"

        viewAdapter = CupTeamPlayerAdapter(dataList)

        recyclerView.adapter = viewAdapter

        sharedViewModel.selectedState.observe(viewLifecycleOwner, Observer { selected ->
            if (selected != null) {
                //ini buat get sport di page sebelumnya
                when (selected) {
                    "find_team" -> {
                        joinTeamBtn.visibility = View.VISIBLE
                        editTeamBtn.visibility = View.GONE
                        findCupBtn.visibility = View.GONE

                    }
                    "create_team" -> {
                        editTeamBtn.visibility = View.VISIBLE
                        findCupBtn.visibility = View.VISIBLE
                        joinTeamBtn.visibility = View.GONE
                    }
                }
            }
            else{

            }
        })

        joinTeamBtn.setOnClickListener(){
            dataList.add(CupTeamMemberData("Player 1", "Player"))
            viewAdapter.notifyDataSetChanged()
            teamMemberText.text = dataList.size.toString() + " Player(s)"

            joinTeamBtn.visibility = View.GONE
            leaveTeamBtn.visibility = View.VISIBLE
        }

        leaveTeamBtn.setOnClickListener(){
            dataList.remove(CupTeamMemberData("Player 1", "Player"))
            viewAdapter.notifyDataSetChanged()
            teamMemberText.text = dataList.size.toString() + " Player(s)"

            joinTeamBtn.visibility = View.VISIBLE
            leaveTeamBtn.visibility = View.GONE
        }


        findCupBtn.setOnClickListener(){
            val frag = CupList()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

        editTeamBtn.setOnClickListener(){
            val frag = CupEditTeam()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}