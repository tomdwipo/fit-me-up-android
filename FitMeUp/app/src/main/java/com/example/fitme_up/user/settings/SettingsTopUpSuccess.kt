package com.example.fitme_up.user.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.fitme_up.user.HomeUserFragment
import com.example.fitme_up.R

class SettingsTopUpSuccess : Fragment() {

    private lateinit var returnBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_top_up_success, container, false)

        returnBtn = view.findViewById(R.id.btn_return)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        returnBtn.setOnClickListener(){
            val frag = HomeUserFragment()
            val tran = fragmentManager?.beginTransaction()
            fragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}