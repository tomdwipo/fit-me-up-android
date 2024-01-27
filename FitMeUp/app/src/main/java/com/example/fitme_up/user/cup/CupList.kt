package com.example.fitme_up.user.cup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.cupAdapter.CupListAdapter
import com.example.fitme_up.user.dataset.CupData
import com.example.fitme_up.user.dataset.VenueImageData

class CupList : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var findTeamBtn: Button
    private lateinit var createTeamBtn: Button
    private lateinit var manageTeamBtn: Button
    private lateinit var teamWarningText: TextView
    private lateinit var cupDomicileText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_cup_list)
        cupDomicileText = view.findViewById(R.id.text_cup_list_domicile)
        findTeamBtn = view.findViewById(R.id.btn_cup_find_team)
        createTeamBtn = view.findViewById(R.id.btn_cup_create_team)
        manageTeamBtn = view.findViewById(R.id.btn_cup_manage_team)
        teamWarningText = view.findViewById(R.id.text_cup_join_team_warning)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cupDomicileText.text = "Available Cup in Jakarta Timur"

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            CupData("Champion Cup", "Badminton", "Zazila (Futsal & Badminton)", "2 January 2024"),
            CupData("Badminton Super Cup", "Badminton", "Halim Futsal & Badminton", "3 January 2024"),
            CupData("Super Cup", "Badminton", "Halim Futsal & Badminton", "5 January 2024"),
            CupData("Relaxing Cup", "Badminton", "GOR BFM Pulogebang", "8 January 2024"),
        )

        Log.d("print", dataList2.toString())

        viewAdapter = CupListAdapter(dataList2, this)

        recyclerView.adapter = viewAdapter

        val createVisible = createTeamBtn.visibility
        val findVisible = findTeamBtn.visibility

        if(createVisible == View.VISIBLE && findVisible == View.VISIBLE){
            teamWarningText.visibility = View.VISIBLE
        }
        else{
            teamWarningText.visibility = View.GONE
        }

        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = activity?.supportFragmentManager?.backStackEntryCount
            if (backStackCount != null) {
                if (backStackCount > 0) {

                    val fm = fragmentManager.findFragmentByTag("cup_team_details")

                    if (fm != null) {
                        Log.d("print", "from lfg success")

                        manageTeamBtn.visibility = View.VISIBLE
                        createTeamBtn.visibility = View.GONE
                        findTeamBtn.visibility = View.GONE
                        teamWarningText.visibility = View.GONE
                        //tambah logic data kalo dari lfg (masukin ke lfg DB)
                    }
                    else{
                        createTeamBtn.visibility = View.VISIBLE
                        findTeamBtn.visibility = View.VISIBLE
                        manageTeamBtn.visibility = View.GONE
                        teamWarningText.visibility = View.VISIBLE
                    }
                }
            }
        }

        findTeamBtn.setOnClickListener(){
            val frag = CupFindTeam()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

        createTeamBtn.setOnClickListener(){
            val frag = CupCreateTeam()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

        manageTeamBtn.setOnClickListener(){
            val frag = CupEditTeam()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

    }

}