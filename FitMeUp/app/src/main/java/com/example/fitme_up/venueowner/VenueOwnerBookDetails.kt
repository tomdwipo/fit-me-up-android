package com.example.fitme_up.venueowner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.R
import com.example.fitme_up.viewmodel.ViewModelFragmentTag

class VenueOwnerBookDetails : Fragment() {

    private lateinit var bottomBtn: ConstraintLayout
    private lateinit var sharedViewModel: ViewModelFragmentTag

    private lateinit var requestStatusText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_venue_owner_book_details, container, false)

        bottomBtn = view.findViewById(R.id.layout_payment)
        requestStatusText = view.findViewById(R.id.text_venue_order_status)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelFragmentTag::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.selectedState.observe(viewLifecycleOwner, Observer { selected ->
            if (selected != null) {
                //ini buat get sport di page sebelumnya
                when (selected) {
                    "waiting_approval" -> {
                        Log.d("print", "$selected")
                        bottomBtn.visibility = View.VISIBLE
                        requestStatusText.text = "Waiting for Approval"
                    }
                    "on_progress" -> {
                        Log.d("print", "$selected")
                        bottomBtn.visibility = View.GONE
                        requestStatusText.text = "On Progress"
                    }
                }
            }
            else{
                bottomBtn.visibility = View.GONE
                requestStatusText.text = "Declined"
            }
        })

    }
}