package com.example.fitme_up.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.LoginActivity
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelSelectSport
import com.example.fitme_up.blueprint.RegisterUserInput

class RegisterUserFragment3 : Fragment() {

    private lateinit var registerBtn: Button
    private lateinit var sharedViewModel: ViewModelSelectSport

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_user3, container, false)

        registerBtn = view.findViewById(R.id.registerButtonUser3)
        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelSelectSport::class.java)

        val bundle = arguments?.getParcelable("bundle", RegisterUserInput::class.java)

        if (bundle != null) {
            Log.d("print", bundle.fullName)
            Log.d("print", bundle.email)
            Log.d("print", bundle.password)
            Log.d("print", bundle.dateBirth)
            Log.d("print", bundle.favSportIds.toString())
            Log.d("print", bundle.domicileId.toString())
        }

//        sharedViewModel.selectedState.observe(viewLifecycleOwner, Observer { selected ->
//            if (selected != null) {
//                //ini buat get sport di page sebelumnya
//                when (selected) {
//                    1 -> {
//                        Toast.makeText(requireContext(), "Selected state: $selected", Toast.LENGTH_SHORT).show()
//                    }
//                    2 -> {
//                        Toast.makeText(requireContext(), "Selected state: $selected", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            }
//        })

        registerBtn.setOnClickListener() {
            activity?.let{
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        }

        return view
    }

}