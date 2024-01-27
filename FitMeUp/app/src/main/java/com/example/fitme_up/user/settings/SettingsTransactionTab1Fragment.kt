package com.example.fitme_up.user.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.settingsAdapter.SettingsTransactionTab1Adapter
import com.example.fitme_up.user.dataset.SettingsTopUpData

class SettingsTransactionTab1Fragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_transaction_tab1, container, false)

        recyclerView = view.findViewById(R.id.recycler_settings_transaction_topup)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList = listOf(
            SettingsTopUpData("#1231225323423", "28 December 2023", 25000),
            SettingsTopUpData("#1386672443423", "25 December 2023", 60000),
            SettingsTopUpData("#1231789000423", "22 October 2023", 50000),
            SettingsTopUpData("#12312e5746423", "10 October 2023", 150000),
            SettingsTopUpData("#1356457323423", "23 September 2023", 23000),
            SettingsTopUpData("#1237845003423", "22 September 2023", 50000)
        )

        viewAdapter = SettingsTransactionTab1Adapter(dataList, this)
        recyclerView.adapter = viewAdapter
    }
}