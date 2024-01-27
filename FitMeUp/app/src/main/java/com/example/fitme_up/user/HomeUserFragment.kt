package com.example.fitme_up.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.HomeAdapter
import com.example.fitme_up.user.adapter.VenueAdapter
import com.example.fitme_up.user.dataset.ActivityData
import com.example.fitme_up.user.dataset.VenueData

class HomeUserFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewAdapter2: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewManager2: RecyclerView.LayoutManager

    companion object {
        fun newInstance() = HomeUserFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = requireView().findViewById(R.id.activityRecycleList)
        recyclerView2 = requireView().findViewById(R.id.venueRecycleListTab1)
        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewManager2 = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager
        recyclerView2.layoutManager = viewManager2

        val dataList = listOf(
            ActivityData("Image 1", "LFG 1", "Jakarta Timur"),
            ActivityData("Image 2", "LFG 2", "Jakarta Timur"),
            ActivityData("Image 3", "LFG 3", "Jakarta Timur"),
            ActivityData("Image 4", "LFG 4", "Jakarta Timur"),
            ActivityData("Image 5", "LFG 5", "Jakarta Timur")
        )

        val dataList2 = listOf(
            VenueData("Tifosi", "Badminton", "Jakarta Timur", 50000),
            VenueData("Zy Futsal", "Badminton", "Jakarta Timur", 150000),
            VenueData("Top Sport Center - Duren Sawit", "Futsal", "Jakarta Timur", 75000)
        )
        viewAdapter = HomeAdapter(dataList)
        viewAdapter2 = VenueAdapter(dataList2, this)

        recyclerView.adapter = viewAdapter
        recyclerView2.adapter = viewAdapter2
    }

}