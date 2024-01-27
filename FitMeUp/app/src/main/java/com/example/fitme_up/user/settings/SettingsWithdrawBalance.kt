package com.example.fitme_up.user.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fitme_up.R

class SettingsWithdrawBalance : Fragment() {

    private lateinit var withdrawBtn: Button
    private lateinit var withdrawCancelBtn: Button
    private lateinit var withdrawBalInput: EditText

    private lateinit var playerDomicileText: TextView
    private lateinit var playerRoleText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_withdraw_balance, container, false)

        withdrawBtn = view.findViewById(R.id.btn_profile_withdraw)
        withdrawCancelBtn = view.findViewById(R.id.btn_profile_cancel_withdraw)
        withdrawBalInput = view.findViewById(R.id.input_settings_withdraw_amount)

        playerDomicileText = view.findViewById(R.id.text_player_domicile)
        playerRoleText = view.findViewById(R.id.text_player_role)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerDomicileText.text = "Jakarta Timur"
        playerRoleText.text = "User"

        withdrawBtn.setOnClickListener(){

            val bal = withdrawBalInput.text.toString()
            val alertDialog = getActivity()?.let {

                AlertDialog.Builder(it)

                    .setTitle("Confirm Withdraw")
                    .setMessage("Are you sure you want to withdraw with amount of Rp. $bal")
                    .setPositiveButton("Yes") { _, _ ->
                        val frag = SettingsWithdrawSuccess()
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

        withdrawCancelBtn.setOnClickListener(){

        }

    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }

}