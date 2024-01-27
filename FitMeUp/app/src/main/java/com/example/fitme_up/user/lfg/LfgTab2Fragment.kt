package com.example.fitme_up.user.lfg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.lfgAdapter.LfgTab2Adapter
import com.example.fitme_up.user.dataset.LfgData

class LfgTab2Fragment : Fragment(), OnButtonClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lfg_tab2, container, false)

        recyclerView = view.findViewById(R.id.recycler_lfg_tab2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            LfgData("LFG Fun Match", "Badminton", "Jakarta Barat", "09:00"),
            LfgData("LFG", "Badminton", "Jakarta Selatan", "12:00"),
            LfgData("LFG Competitive", "Futsal", "Jakarta Pusat", "14:00"),
            LfgData("LFG Pro Only", "Futsal", "Jakarta Utara", "18:00"),
            LfgData("Quick Match LFG", "Futsal", "Jakarta Timur", "20:00")
        )

        viewAdapter = LfgTab2Adapter(dataList2, this)

        recyclerView.adapter = viewAdapter
    }

    override fun onButtonClick() {
        val frag = LfgDetails()
        val tran = fragmentManager?.beginTransaction()
        tran?.replace(R.id.fragment_cont, frag)?.commit()
        tran?.addToBackStack(null)
    }
}