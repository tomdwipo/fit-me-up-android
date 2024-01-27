package com.example.fitme_up.user.cup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelFragmentTag

class CupCreateTeam : Fragment(){

    private lateinit var createTeamBtn: Button
    private lateinit var domicileSpinner: Spinner
    private lateinit var sportSpinner: Spinner

    private lateinit var sharedViewModel: ViewModelFragmentTag

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cup_create_team, container, false)

        createTeamBtn = view.findViewById(R.id.btn_cup_team_create)
        domicileSpinner = view.findViewById(R.id.spinner_cup_team_domicile)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelFragmentTag::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFragmentWithTag(this, "cup_create_team")

        val domicile_list = resources.getStringArray(R.array.domicile_list)
        if (domicileSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, domicile_list
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            domicileSpinner.adapter = adapter
        }

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

            AlertDialog.Builder(it)
                .setTitle("Confirm Create Team")
                .setMessage("Are you sure you want to create this team?")
                .setPositiveButton("Yes") { _, _ ->
                    sharedViewModel.updateSelectedState("create_team")
                    val frag = CupTeamDetails()
                    val tran = fragmentManager?.beginTransaction()
                    tran?.addToBackStack(null)
                    tran?.replace(R.id.fragment_cont, frag)?.commit()
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        createTeamBtn.setOnClickListener {
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String?) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }
}