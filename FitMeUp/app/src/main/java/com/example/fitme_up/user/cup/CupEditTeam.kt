package com.example.fitme_up.user.cup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.cupAdapter.CupEditTeamAdapter
import com.example.fitme_up.user.dataset.CupTeamMemberData

class CupEditTeam : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var inviteTeamBtn: Button
    private lateinit var saveTeamBtn: Button
    private lateinit var cancelEditBtn: Button
    private lateinit var sportSpinner: Spinner

    private lateinit var teamMemberText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_edit_team, container, false)

        recyclerView = view.findViewById(R.id.recycler_team_player)

        inviteTeamBtn = view.findViewById(R.id.btn_cup_team_invite)
        saveTeamBtn = view.findViewById(R.id.btn_cup_team_edit_save)
        cancelEditBtn = view.findViewById(R.id.btn_cup_team_edit_cancel)

        teamMemberText = view.findViewById(R.id.text_team_num)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = arrayListOf(
            CupTeamMemberData("John Rico", "Room Master"),
            CupTeamMemberData("Jon Rico", "Player"),
            CupTeamMemberData("Jhon Rico", "Player"),
            CupTeamMemberData("Juan Rico", "Player"),
            CupTeamMemberData("Joe Rico", "Player"),
            CupTeamMemberData("Jo Rico", "Player"),
            CupTeamMemberData("Jojo Rico", "Player")
        )

        teamMemberText.text = dataList.size.toString() + " Player(s)"

        sportSpinner = view.findViewById(R.id.spinner_cup_team_sport)
        val sport_list = resources.getStringArray(R.array.sport_list)
        if (sportSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, sport_list
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sportSpinner.adapter = adapter
        }

        val alertDialog = getActivity()?.let {
            val editText = EditText(it)
            editText.isSingleLine = true
            AlertDialog.Builder(it)
                .setTitle("Confirm Add Player")
                .setMessage("Are you sure you want to add this player?")
                .setView(editText)
                .setPositiveButton("Add Player") { _, _ ->
                    val userInput = editText.text.toString() //kasih logic dari DB buat get data player
                    dataList.add(CupTeamMemberData(userInput, "Player"))
                    viewAdapter.notifyDataSetChanged()
                    teamMemberText.text = dataList.size.toString() + " Player(s)"
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        val alertDialog2 = getActivity()?.let {
            AlertDialog.Builder(it)
                .setTitle("Cancel Edit Team")
                .setMessage("Cancel editing this team?")
                .setPositiveButton("Yes") { _, _ ->
                    activity?.supportFragmentManager?.popBackStack()
                }
                .setNegativeButton("No") { _, _ -> }
                .create()
        }

        val alertDialog3 = getActivity()?.let {
            AlertDialog.Builder(it)
                .setTitle("Save Edit Team")
                .setMessage("Are you sure you want to save this changes?")
                .setPositiveButton("Yes") { _, _ ->
                    activity?.supportFragmentManager?.popBackStack()
                }
                .setNegativeButton("No") { _, _ -> }
                .create()
        }

        inviteTeamBtn.setOnClickListener {
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }

        cancelEditBtn.setOnClickListener {
            if (alertDialog2 != null) {
                showAlertDialog(alertDialog2)
            }
        }

        saveTeamBtn.setOnClickListener {
            if (alertDialog3 != null) {
                showAlertDialog(alertDialog3)
            }
        }

        viewAdapter = CupEditTeamAdapter(dataList) { itemToRemove ->
            dataList.remove(itemToRemove)
            viewAdapter.notifyDataSetChanged()
            teamMemberText.text = dataList.size.toString() + " Player(s)"
        }

        recyclerView.adapter = viewAdapter

    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

}