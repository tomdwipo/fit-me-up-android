package com.example.fitme_up.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.blueprint.Domicile
import com.example.fitme_up.blueprint.FavSportData
import com.example.fitme_up.blueprint.Profiles
import com.example.fitme_up.blueprint.RegisterUserInput
import com.example.fitme_up.blueprint.Users

class ViewModelList() : ViewModel() {

    //DOMICILE
    private val _domicile = MutableLiveData<List<Domicile>>()
    val domicile: LiveData<List<Domicile>> = _domicile

    //FAV-SPORT
    private val _favSport = MutableLiveData<List<FavSportData>>()
    val favSport: LiveData<List<FavSportData>> = _favSport

    suspend fun fetchDomicile() {
        val response = RetrofitClient.instance.getDomicilePost()
        _domicile.value = response.data
    }
    suspend fun fetchFavSport() {
        val response = RetrofitClient.instance.getFavSport()
        _favSport.value = response.data
    }
//
//    suspend fun postRegisterUser(data: RegisterUserInput){
//        val response = RetrofitClient.instance.postRegisterProfiles(data)
//        _registerProfiles.value = response.data
//    }


}