package com.example.fitme_up.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.adapter.DomicileListAdapter
import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.blueprint.RegisterVenueOwnerInput
import com.example.fitme_up.blueprint.ValidationError
import com.example.fitme_up.viewmodel.ViewModelList
import com.example.fitme_up.viewmodel.ViewModelSelectSport
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVenueOwnerFragment2 : Fragment() {

    private lateinit var btnBadminton: ImageButton
    private lateinit var btnFutsal: ImageButton
    private lateinit var btnSelectSport: Button
    private lateinit var badmintonText: TextView
    private lateinit var futsalText: TextView
    private lateinit var domicileSpinner: Spinner
    private lateinit var sharedViewModel: ViewModelSelectSport

    //RETROFIT
    private lateinit var viewModel: ViewModelList
    private lateinit var domicileAdapter: DomicileListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_venue_owner2, container, false)

        btnBadminton = view.findViewById(R.id.btn_select_badminton)
        btnFutsal = view.findViewById(R.id.btn_select_futsal)
        btnSelectSport = view.findViewById(R.id.btn_register_venue_owner_select_sport)
        futsalText = view.findViewById(R.id.text_select_futsal)
        badmintonText = view.findViewById(R.id.text_select_badminton)
        domicileSpinner = view.findViewById(R.id.spinner_venue_owner_register_domicile)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelSelectSport::class.java)

        viewModel = ViewModelProvider(this).get(ViewModelList::class.java)

        lifecycleScope.launch {
            viewModel.fetchDomicile()
            domicileAdapter.notifyDataSetChanged()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.updateSelectedState(null)

        val bundle = arguments?.getParcelable("bundle", RegisterVenueOwnerInput::class.java)

        viewModel = ViewModelProvider(this).get(ViewModelList::class.java)

        lifecycleScope.launch {
            viewModel.fetchDomicile()
            domicileAdapter.notifyDataSetChanged()
        }

        btnBadminton.setBackgroundResource(R.drawable.button_toggle_selector)
        btnFutsal.setBackgroundResource(R.drawable.button_toggle_selector)

        context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
        context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.birutua)) }

        //ini buat select sport
        btnBadminton.setOnClickListener {
            btnBadminton.isSelected = !btnBadminton.isSelected
            if (btnBadminton.isSelected) {
                btnFutsal.isSelected = false
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.white)) }
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(1)
//                bundle?.venueSports?.setFavSport(1)
//                bundle?.setFavSport(1)
//                bundle?.deleteFavSport(2)
            } else {
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(null)
//                bundle?.deleteFavSport(1)
//                bundle?.venueSports?.deleteFavSport(1)
            }
        }

        btnFutsal.setOnClickListener() {
            btnFutsal.isSelected = !btnFutsal.isSelected
            if (btnFutsal.isSelected) {
                btnBadminton.isSelected = false
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.white)) }
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(2)
//                bundle?.venueSports?.setFavSport(2)
//                bundle?.setFavSport(2)
//                bundle?.deleteFavSport(1)
            } else {
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(null)
//                bundle?.deleteFavSport(2)
//                bundle?.venueSports?.deleteFavSport(2)
            }
        }

        domicileAdapter = DomicileListAdapter(requireContext(), 5, listOf())

        viewModel.domicile.observe(viewLifecycleOwner, Observer { domicile ->
            domicile?.let {
                val domicileArray = arrayListOf<String>()
                domicileAdapter = DomicileListAdapter(requireContext(), domicileAdapter.count, it)
                for (a in 0..<domicileAdapter.count) {
                    domicileArray.add(domicileAdapter.items[a].domicile_name)
                }
                val spinnerArray = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item, domicileArray
                )
                spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                domicileSpinner.adapter = spinnerArray
            }
        })

        btnSelectSport.setOnClickListener() {
            if (btnBadminton.isSelected || btnFutsal.isSelected) {

                bundle?.setDomicile(domicileSpinner.selectedItemPosition)

                val fetch = RetrofitClient.instance.postRegisterVenueOwner(bundle)

                fetch.enqueue(object : Callback<DataResponse> {
                    override fun onResponse(
                        call: Call<DataResponse>,
                        response: Response<DataResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()

                        } else{
                            val errorMsg =  response.errorBody()

                            val errorResponse = Gson().fromJson(errorMsg?.charStream(), ValidationError::class.java)
                            if (errorResponse.error.message.size == 8) {
                                if (errorResponse.error.message[0].field == "venueName" &&
                                    errorResponse.error.message[1].field == "venueAddress" &&
                                    errorResponse.error.message[2].field == "venueSports" &&
                                    errorResponse.error.message[3].field == "venueSchedules" &&
                                    errorResponse.error.message[4].field == "venueTimeOpen" &&
                                    errorResponse.error.message[5].field == "venueTimeClose" &&
                                    errorResponse.error.message[6].field == "venuePhotos" &&
                                    errorResponse.error.message[7].field == "venueDescription"
                                ) {
                                    val bundle = Bundle()
                                    bundle.putParcelable("bundle", bundle)

                                    val frag = RegisterVenueOwnerFragment3()
                                    frag.arguments = bundle

                                    val tran = fragmentManager?.beginTransaction()
                                    tran?.replace(R.id.frame_register_cont, frag)
                                    tran?.commit()
                                    tran?.addToBackStack(null)
                                }
                            }
                            Log.d("print", errorResponse.toString())
                        }
                    }

                    override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                        Log.d("print", t.toString())
                    }

                })
            }
        }
    }
}