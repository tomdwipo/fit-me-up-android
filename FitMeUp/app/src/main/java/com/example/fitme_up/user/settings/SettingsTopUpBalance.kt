package com.example.fitme_up.user.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.fitme_up.R

class SettingsTopUpBalance : Fragment() {

    private lateinit var topupBtn: Button
    private lateinit var topupCancelBtn: Button
    private lateinit var topupBalInput: EditText

    private lateinit var playerDomicileText: TextView
    private lateinit var playerRoleText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_top_up_balance, container, false)

        topupBtn = view.findViewById(R.id.btn_profile_topup)
        topupCancelBtn = view.findViewById(R.id.btn_profile_cancel_topup)
        topupBalInput = view.findViewById(R.id.input_settings_topup_amount)

        playerDomicileText = view.findViewById(R.id.text_player_domicile)
        playerRoleText = view.findViewById(R.id.text_player_role)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerDomicileText.text = "Jakarta Timur"
        playerRoleText.text = "User"

        topupBtn.setOnClickListener(){

            val bal = topupBalInput.text.toString()
            val alertDialog = getActivity()?.let {

                AlertDialog.Builder(it)

                    .setTitle("Confirm Top Up")
                    .setMessage("Are you sure you want to top up with amount of Rp. $bal")
                    .setPositiveButton("Yes") { _, _ ->
                        val frag = SettingsTopUpSuccess()
                        val tran = fragmentManager?.beginTransaction()
                        tran?.addToBackStack(null)
                        tran?.replace(R.id.fragment_cont, frag)?.commit()
                    }
                    .setNegativeButton("Cancel") { _, _ -> }
                    .create()
            }
            Log.d("print", "$bal")
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }

        topupCancelBtn.setOnClickListener(){

        }

    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

}