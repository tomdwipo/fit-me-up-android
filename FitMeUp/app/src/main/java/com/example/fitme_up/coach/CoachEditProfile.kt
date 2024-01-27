package com.example.fitme_up.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelSelectDay

class CoachEditProfile : Fragment() {

    private lateinit var checkMonday: CheckBox
    private lateinit var checkTuesday: CheckBox
    private lateinit var checkWednesday: CheckBox
    private lateinit var checkThursday: CheckBox
    private lateinit var checkFriday: CheckBox
    private lateinit var checkSaturday: CheckBox
    private lateinit var checkSunday: CheckBox

    private lateinit var btnSave: Button

    private lateinit var sharedViewModel2: ViewModelSelectDay

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_edit_profile, container, false)

        sharedViewModel2 = ViewModelProvider(requireActivity()).get(ViewModelSelectDay::class.java)

        checkMonday = view.findViewById(R.id.check_monday)
        checkTuesday = view.findViewById(R.id.check_tuesday)
        checkWednesday = view.findViewById(R.id.check_wednesday)
        checkThursday = view.findViewById(R.id.check_thursday)
        checkFriday = view.findViewById(R.id.check_friday)
        checkSaturday = view.findViewById(R.id.check_saturday)
        checkSunday = view.findViewById(R.id.check_sunday)

        btnSave = view.findViewById(R.id.btn_save_coach_profile)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alertDialog = getActivity()?.let {

            AlertDialog.Builder(it)
                .setTitle("Save Profile")
                .setMessage("Are you sure you want to update your profile?")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(activity, "Profile Saved", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        btnSave.setOnClickListener() {
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
}