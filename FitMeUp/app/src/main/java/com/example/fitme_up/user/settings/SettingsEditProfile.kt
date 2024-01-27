package com.example.fitme_up.user.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.fitme_up.R

class SettingsEditProfile : Fragment() {

    private lateinit var bankSpinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_edit_profile, container, false)


        bankSpinner = view.findViewById(R.id.spinner_profile_bank_name)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bank_list = resources.getStringArray(R.array.bank_list)
        if (bankSpinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, bank_list
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bankSpinner.adapter = adapter
        }
    }

}