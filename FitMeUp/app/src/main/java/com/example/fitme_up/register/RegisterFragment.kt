package com.example.fitme_up.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class RegisterFragment : Fragment() {

    lateinit var roleUserButton: Button
    lateinit var roleCoachButton: Button
    lateinit var roleOwnerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_role, container, false)

        roleUserButton = view.findViewById(R.id.btnRoleUser)
        roleCoachButton = view.findViewById(R.id.btnRoleCoach)
        roleOwnerButton = view.findViewById(R.id.btnRoleOwner)

        roleUserButton.setOnClickListener() {
            val userFrag = RegisterUserFragment()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.frame_register_cont, userFrag)?.commit()
            tran?.addToBackStack(null)
        }
        roleCoachButton.setOnClickListener() {
            val coachFrag = RegisterCoachFragment()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.frame_register_cont, coachFrag)?.commit()
            tran?.addToBackStack(null)
        }
        roleOwnerButton.setOnClickListener() {
            val coachFrag = RegisterVenueOwnerFragment()
            val tran = fragmentManager?.beginTransaction()
            tran?.replace(R.id.frame_register_cont, coachFrag)?.commit()
            tran?.addToBackStack(null)
        }
        return view
    }
}