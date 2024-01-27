package com.example.fitme_up.user.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class BookingCoachConfirm : Fragment() {

    private lateinit var confirmCoachBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_coach_confirm, container, false)

        confirmCoachBtn = view.findViewById(R.id.confirmCoachBtn)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alertDialog = getActivity()?.let {
            AlertDialog.Builder(it)
                .setTitle("Confirm Book")
                .setMessage("Are you sure you want to book this coach?")
                .setPositiveButton("OK") { _, _ ->
                    val frag = BookingCoachSuccess()
                    val tran = fragmentManager?.beginTransaction()
                    tran?.replace(R.id.fragment_cont, frag)?.commit()
                    tran?.addToBackStack(null)
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        confirmCoachBtn.setOnClickListener {
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