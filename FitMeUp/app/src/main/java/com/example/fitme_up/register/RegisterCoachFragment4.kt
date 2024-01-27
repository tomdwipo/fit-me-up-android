package com.example.fitme_up.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelSelectDay

class RegisterCoachFragment4 : Fragment() {

    private lateinit var layoutMonday: LinearLayout
    private lateinit var layoutTuesday: LinearLayout
    private lateinit var layoutWednesday: LinearLayout
    private lateinit var layoutThursday: LinearLayout
    private lateinit var layoutFriday: LinearLayout
    private lateinit var layoutSaturday: LinearLayout
    private lateinit var layoutSunday: LinearLayout

    private lateinit var registerBtn: Button

    private lateinit var sharedViewModel2: ViewModelSelectDay

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_coach4, container, false)

        registerBtn = view.findViewById(R.id.registerButtonCoach4)

        layoutMonday = view.findViewById(R.id.layout_monday)
        layoutTuesday = view.findViewById(R.id.layout_tuesday)
        layoutWednesday = view.findViewById(R.id.layout_wednesday)
        layoutThursday = view.findViewById(R.id.layout_thursday)
        layoutFriday = view.findViewById(R.id.layout_friday)
        layoutSaturday = view.findViewById(R.id.layout_saturday)
        layoutSunday = view.findViewById(R.id.layout_sunday)

        sharedViewModel2 = ViewModelProvider(requireActivity()).get(ViewModelSelectDay::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel2.selectedDays.observe(viewLifecycleOwner, Observer { updatedList ->
            // Now you can use the updatedList in this fragment
            Log.d("print", "Updated List: $updatedList")
            if (updatedList != null) {
                if(updatedList.contains("Monday")){
                    layoutMonday.visibility = View.VISIBLE
                }
                if(updatedList.contains("Tuesday")){
                    layoutTuesday.visibility = View.VISIBLE
                }
                if(updatedList.contains("Wednesday")){
                    layoutWednesday.visibility = View.VISIBLE
                }
                if(updatedList.contains("Thursday")){
                    layoutThursday.visibility = View.VISIBLE
                }
                if(updatedList.contains("Friday")){
                    layoutFriday.visibility = View.VISIBLE
                }
                if(updatedList.contains("Saturday")){
                    layoutSaturday.visibility = View.VISIBLE
                }
                if(updatedList.contains("Sunday")){
                    layoutSunday.visibility = View.VISIBLE
                }
            }
        })

        val alertDialog = getActivity()?.let {

            AlertDialog.Builder(it)
                .setTitle("Confirm Registration")
                .setMessage("Are you sure you want to register?")
                .setPositiveButton("Yes") { _, _ ->
                    val frag = RegisterCoachFragment5()
                    val tran = fragmentManager?.beginTransaction()
                    tran?.replace(R.id.frame_register_cont, frag)?.commit()
                    tran?.addToBackStack(null)
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        registerBtn.setOnClickListener() {
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