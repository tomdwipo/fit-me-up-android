package com.example.fitme_up.user.lfg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fitme_up.R
import com.example.fitme_up.adapter.DomicileListAdapter
import com.example.fitme_up.adapter.SportListAdapter
import com.example.fitme_up.viewmodel.ViewModelList
import kotlinx.coroutines.launch

class LfgCreateNew : Fragment() {

    private lateinit var btnCreateLfg: Button
    private lateinit var domicileSpinner: Spinner
    private lateinit var sportSpinner: Spinner
    private lateinit var minPlayerInput: EditText

    //RETROFIT
    private lateinit var viewModel: ViewModelList
    private lateinit var sportAdapter: SportListAdapter
    private lateinit var domicileAdapter: DomicileListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lfg_create_new, container, false)

        btnCreateLfg = view.findViewById(R.id.btn_lfg_create)
        minPlayerInput = view.findViewById(R.id.input_lfg_min_player)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFragmentWithTag(this, "lfg_create_new")

        domicileSpinner = view.findViewById(R.id.spinner_lfg_domicile)
        sportSpinner = view.findViewById(R.id.spinner_lfg_sport_type)

        viewModel = ViewModelProvider(this).get(ViewModelList::class.java)
        sportAdapter = SportListAdapter(requireContext(), 2, listOf())

        lifecycleScope.launch {
            viewModel.fetchFavSport()
            viewModel.fetchDomicile()
            sportAdapter.notifyDataSetChanged()
            domicileAdapter.notifyDataSetChanged()
        }

        viewModel.favSport.observe(viewLifecycleOwner, Observer { favSport ->
            favSport?.let {
                val sportArray = arrayListOf<String>()
                sportAdapter = SportListAdapter(requireContext(), sportAdapter.count, it)
                for (a in 0..<sportAdapter.count) {
                    sportArray.add(sportAdapter.items[a].sport_name)
                }
                val spinnerArray = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item, sportArray
                )
                spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sportSpinner.adapter = spinnerArray
            }
        })

        domicileAdapter = DomicileListAdapter(requireContext(), 5, listOf())

        viewModel.domicile.observe(viewLifecycleOwner, Observer { domicile ->
            domicile?.let {
                val sportArray = arrayListOf<String>()
                domicileAdapter = DomicileListAdapter(requireContext(), domicileAdapter.count, it)
                for (a in 0..<domicileAdapter.count) {
                    sportArray.add(domicileAdapter.items[a].domicile_name)
                }
                val spinnerArray = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item, sportArray
                )
                spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                domicileSpinner.adapter = spinnerArray
            }
        })

        sportSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // When a spinner item is selected, change the text view's text
                val a = parent.getItemAtPosition(position).toString()
                if (a == "Badminton") {
                    minPlayerInput.hint = "Enter min. player (max 8)"
                } else {
                    minPlayerInput.hint = "Enter min. player (max 10)"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: If no item is selected, you can have some default text here
                minPlayerInput.hint = "Please select a sport"
            }
        }

        val alertDialog = getActivity()?.let {

            AlertDialog.Builder(it)
                .setTitle("Confirm Create LFG")
                .setMessage("Are you sure you want to create this LFG")
                .setPositiveButton("Yes") { _, _ ->
                    val frag = LfgDetails()
                    val tran = fragmentManager?.beginTransaction()
                    tran?.addToBackStack("lfg_create")
                    tran?.replace(R.id.fragment_cont, frag)?.commit()
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        btnCreateLfg.setOnClickListener {
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

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

}