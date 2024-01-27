package com.example.fitme_up.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.feature_login.presentation.screens.LoginActivity
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelSelectSport

class RegisterCoachFragment5 : Fragment() {

    private lateinit var registerBtn: Button
    private lateinit var sharedViewModel: ViewModelSelectSport

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_coach5, container, false)

        registerBtn = view.findViewById(R.id.registerButtonCoach4)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelSelectSport::class.java)

        sharedViewModel.selectedState.observe(viewLifecycleOwner, Observer { selected ->
            if (selected != null) {
                //ini buat get sport di page sebelumnya
                when (selected) {
                    1 -> {
//                        Toast.makeText(requireContext(), "Selected state: $selected", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
//                        Toast.makeText(requireContext(), "Selected state: $selected", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBtn.setOnClickListener(){
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}