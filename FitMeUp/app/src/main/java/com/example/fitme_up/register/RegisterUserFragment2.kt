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
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.adapter.DomicileListAdapter
import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.viewmodel.ViewModelSelectSport
import com.example.fitme_up.blueprint.RegisterUserInput
import com.example.fitme_up.blueprint.ValidationError
import com.example.fitme_up.viewmodel.ViewModelList
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterUserFragment2 : Fragment() {

    lateinit var selectSportBtn: Button
    private lateinit var badmintonBtn: ImageButton
    private lateinit var futsalBtn: ImageButton
    private lateinit var badmintonText: TextView
    private lateinit var futsalText: TextView
    private lateinit var sharedViewModel: ViewModelSelectSport
    private lateinit var domicileSpinner: Spinner

    //RETROFIT
    private lateinit var viewModel: ViewModelList
    private lateinit var domicileAdapter: DomicileListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_user2, container, false)

        selectSportBtn = view.findViewById(R.id.btn_register_user_select_sport)
        badmintonBtn = view.findViewById(R.id.btn_select_badminton)
        badmintonText = view.findViewById(R.id.text_select_badminton)
        futsalBtn = view.findViewById(R.id.btn_select_futsal)
        futsalText = view.findViewById(R.id.text_select_futsal)
        domicileSpinner = view.findViewById(R.id.spinner_user_register_domicile)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelSelectSport::class.java)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getParcelable("bundle", RegisterUserInput::class.java)

        sharedViewModel.updateSelectedState(null)

        viewModel = ViewModelProvider(this).get(ViewModelList::class.java)

        lifecycleScope.launch {
            viewModel.fetchDomicile()
            domicileAdapter.notifyDataSetChanged()
        }

        badmintonBtn.setBackgroundResource(R.drawable.button_toggle_selector)
        futsalBtn.setBackgroundResource(R.drawable.button_toggle_selector)

        context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
        context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.birutua)) }

        badmintonBtn.setOnClickListener {
            badmintonBtn.isSelected = !badmintonBtn.isSelected
            if (badmintonBtn.isSelected) {
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.white)) }
                sharedViewModel.updateSelectedState(1)
                bundle?.setFavSport(sharedViewModel.selectedState.value)
            } else {
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(null)
                bundle?.deleteFavSport(1)
            }
        }

        futsalBtn.setOnClickListener() {
            futsalBtn.isSelected = !futsalBtn.isSelected
            if (futsalBtn.isSelected) {
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.white)) }
                sharedViewModel.updateSelectedState(2)
                bundle?.setFavSport(sharedViewModel.selectedState.value)
            } else {
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(null)
                bundle?.deleteFavSport(2)
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

        val alertDialog = getActivity()?.let {

            AlertDialog.Builder(it)
                .setTitle("Confirm Registration")
                .setMessage("Are you sure you want to register?")
                .setPositiveButton("Yes") { _, _ ->
                    bundle?.setDomicile(domicileSpinner.selectedItemPosition)

                    val fetch = RetrofitClient.instance.postRegisterUser(bundle)

                    fetch.enqueue(object : Callback<DataResponse> {
                        override fun onResponse(
                            call: Call<DataResponse>,
                            response: Response<DataResponse>
                        ) {
                            if (response.isSuccessful){
                                Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
                                val frag = RegisterUserFragment3()
                                val tran = fragmentManager?.beginTransaction()
                                tran?.replace(R.id.frame_register_cont, frag)?.commit()
                                tran?.addToBackStack(null)
                            } else{
                                val errorMsg =  response.errorBody()

                                val errorResponse = Gson().fromJson(errorMsg?.charStream(), ValidationError::class.java)
                                Log.d("print", errorResponse.toString())
                            }
                        }

                        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                            Log.d("print", t.toString())
                        }

                    })
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
        }

        selectSportBtn.setOnClickListener() {

                if (alertDialog != null) {
                    showAlertDialog(alertDialog)
                }

        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }
}