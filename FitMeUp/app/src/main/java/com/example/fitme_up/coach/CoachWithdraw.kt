package com.example.fitme_up.coach

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

class CoachWithdraw : Fragment() {

    private lateinit var coachWithdrawBtn: Button
    private lateinit var coachWithdrawCancelBtn: Button
    private lateinit var coachWithdrawBalInput: EditText

    private lateinit var coachNameText: TextView
    private lateinit var coachDomicileText: TextView
    private lateinit var coachRoleText: TextView
    private lateinit var coachBankText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_withdraw_balance, container, false)

        coachWithdrawBtn = view.findViewById(R.id.btn_profile_withdraw)
        coachWithdrawCancelBtn = view.findViewById(R.id.btn_profile_cancel_withdraw)
        coachWithdrawBalInput = view.findViewById(R.id.input_settings_withdraw_amount)

        coachNameText = view.findViewById(R.id.text_player_name)
        coachDomicileText = view.findViewById(R.id.text_player_domicile)
        coachRoleText = view.findViewById(R.id.text_player_role)
        coachBankText = view.findViewById(R.id.text_bank_account_name)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coachNameText.text = "Mr. Coach"
        coachDomicileText.text = "Jakarta Selatan"
        coachRoleText.text = "Coach"
        coachBankText.text = "Mr. Coach"

        coachWithdrawBtn.setOnClickListener(){

            val bal = coachWithdrawBalInput.text.toString()
            val alertDialog = getActivity()?.let {

                AlertDialog.Builder(it)

                    .setTitle("Confirm Withdraw")
                    .setMessage("Are you sure you want to withdraw with amount of Rp. $bal")
                    .setPositiveButton("Yes") { _, _ ->
                        val frag = CoachWithdrawSuccess()
                        val tran = fragmentManager?.beginTransaction()
                        tran?.addToBackStack(null)
                        tran?.replace(R.id.fragment_cont, frag)?.commit()
                    }
                    .setNegativeButton("Cancel") { _, _ -> }
                    .create()
            }
            Log.d("print", bal)
            if (alertDialog != null) {
                showAlertDialog(alertDialog)
            }
        }

        coachWithdrawCancelBtn.setOnClickListener(){

        }

    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }
}