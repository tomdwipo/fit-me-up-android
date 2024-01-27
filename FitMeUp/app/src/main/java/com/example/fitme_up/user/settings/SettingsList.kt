package com.example.fitme_up.user.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnItemClickListener
import com.example.fitme_up.R
import com.example.fitme_up.blueprint.MainData
import com.example.fitme_up.user.adapter.settingsAdapter.SettingsAdapter
import com.example.fitme_up.user.dataset.SettingsData
import com.example.fitme_up.viewmodel.ViewModelList
import kotlinx.coroutines.launch

class SettingsList : Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //retrofit
//    private lateinit var viewModel: ViewModelList
//    private lateinit var adapter: SettingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_settings)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val settingsList = listOf(
            SettingsData(R.drawable.ic_edit_profile, "Edit Profile"),
            SettingsData(R.drawable.ic_topup_bal, "Top Up Balance"),
            SettingsData(R.drawable.ic_topup_withdraw, "Withdraw Balance"),
            SettingsData(R.drawable.ic_topup_history, "Transaction History")
        )

        viewAdapter = SettingsAdapter(settingsList, this)
        recyclerView.adapter = viewAdapter

        //RETROFIT GET FROM DB
//        viewModel = ViewModelProvider(this).get(ViewModelList::class.java)
//        adapter = SettingsAdapter(listOf(), this, this)
//
//        viewManager = LinearLayoutManager(context)
//        recyclerView.layoutManager = viewManager
//
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.adapter = adapter

//        lifecycleScope.launch {
//            viewModel.fetchDomicile()
//            adapter.notifyDataSetChanged()
//        }
//
//        viewModel.domicile.observe(viewLifecycleOwner, Observer { domicile ->
//            domicile?.let {
//                adapter = SettingsAdapter(it, this, this)
//                recyclerView.adapter = adapter
//            }
//        })
    }

    override fun onItemClick(position: Int) {
        val fragment: Fragment = when (position) {
            0 -> SettingsEditProfile()
            1 -> SettingsTopUpBalance()
            2 -> SettingsWithdrawBalance()
            else -> SettingsTransactionHistory()
        }

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}