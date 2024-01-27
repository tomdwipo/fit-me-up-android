package com.example.fitme_up.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.blueprint.RegisterCoachInput
import com.example.fitme_up.blueprint.RegisterUserInput
import com.example.fitme_up.blueprint.ValidationError
import com.example.fitme_up.viewmodel.ViewModelSelectSport
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCoachFragment2 : Fragment() {

    private lateinit var btnBadminton: ImageButton
    private lateinit var btnFutsal: ImageButton
    private lateinit var btnSelectSport: Button
    private lateinit var badmintonText: TextView
    private lateinit var futsalText: TextView
    private lateinit var sharedViewModel: ViewModelSelectSport

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_coach2, container, false)

        btnBadminton = view.findViewById(R.id.btn_select_badminton)
        btnFutsal = view.findViewById(R.id.btn_select_futsal)
        btnSelectSport = view.findViewById(R.id.btn_register_coach_select_sport)
        futsalText = view.findViewById(R.id.text_select_futsal)
        badmintonText = view.findViewById(R.id.text_select_badminton)

        sharedViewModel = ViewModelProvider(requireActivity()).get(ViewModelSelectSport::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getParcelable("bundle", RegisterCoachInput::class.java)

        sharedViewModel.updateSelectedState(null)

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
                bundle?.setFavSport(sharedViewModel.selectedState.value)
                bundle?.deleteFavSport(2)
            } else {
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(null)
                bundle?.deleteFavSport(2)
            }
        }

        btnFutsal.setOnClickListener() {
            btnFutsal.isSelected = !btnFutsal.isSelected
            if (btnFutsal.isSelected) {
                btnBadminton.isSelected = false
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.white)) }
                context?.let { it1 -> badmintonText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(2)
                bundle?.setFavSport(sharedViewModel.selectedState.value)
                bundle?.deleteFavSport(1)

            } else {
                context?.let { it1 -> futsalText.setTextColor(it1.getColor(R.color.birutua)) }
                sharedViewModel.updateSelectedState(null)
                bundle?.deleteFavSport(2)
            }
        }

        btnSelectSport.setOnClickListener() {

            val fetch = RetrofitClient.instance.postRegisterCoach(bundle)

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
                        if (errorResponse.error.message.size == 1){
                            if (errorResponse.error.message[0].field.equals("schedules")){
                                val bundle = Bundle()
                                bundle.putParcelable("bundle", bundle)

                                val frag = RegisterCoachFragment3()
                                frag.arguments = bundle

                                val tran = fragmentManager?.beginTransaction()
                                tran?.replace(R.id.frame_register_cont, frag)
                                tran?.commit()
                                tran?.addToBackStack(null)
                            }
                        }
                        else{
                            Log.d("print", errorResponse.toString())
                        }

                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d("print", t.toString())
                }

            })

//            if (btnBadminton.isSelected || btnFutsal.isSelected) {
//                val frag = RegisterCoachFragment3()
//                val tran = fragmentManager?.beginTransaction()
//                tran?.replace(R.id.frame_register_cont, frag)?.commit()
//                tran?.addToBackStack(null)
//            }

        }
    }
}