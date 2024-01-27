package com.example.fitme_up.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.coach.adapter.CoachHistoryAdapter
import com.example.fitme_up.coach.dataset.CoachingData

class CoachRequestHistory : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_request_history, container, false)

        recyclerView = view.findViewById(R.id.recycler_coach_coaching_history)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            CoachingData("Ciputat One Futsal", "Jakarta Selatan", "Futsal", "10 December 09:00", 2),
            CoachingData("Futsal & Badminton Arena 68", "Jakarta Utara", "Futsal", "11 December 16:00", 2),
            CoachingData("Champion Futsal", "Jakarta Barat", "Futsal", "12 December 08:00", 1)
        )
        viewAdapter = CoachHistoryAdapter(dataList2, this)
        recyclerView.adapter = viewAdapter

        addFragmentWithTag(this, "coach_history")

    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

}