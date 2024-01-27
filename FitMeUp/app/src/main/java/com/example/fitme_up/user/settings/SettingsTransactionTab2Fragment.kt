package com.example.fitme_up.user.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.settingsAdapter.SettingsTransactionTab2Adapter
import com.example.fitme_up.user.dataset.SettingsWithdrawData

class SettingsTransactionTab2Fragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_transaction_tab2, container, false)

        recyclerView = view.findViewById(R.id.recycler_settings_transaction_withdraw)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = listOf(
            SettingsWithdrawData("#2436734534566", "26 December 2023", 25000),
            SettingsWithdrawData("#3464566745555", "24 December 2023", 60000),
            SettingsWithdrawData("#2234230980677", "23 October 2023", 50000),
            SettingsWithdrawData("#6784569469900", "14 October 2023", 150000),
            SettingsWithdrawData("#2344363453475", "25 September 2023", 23000),
            SettingsWithdrawData("#2357300045243", "21 September 2023", 50000)
        )

        viewAdapter = SettingsTransactionTab2Adapter(dataList, this)
        recyclerView.adapter = viewAdapter
    }
}