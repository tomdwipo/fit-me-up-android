package com.example.fitme_up.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelSelectSport : ViewModel() {

    private val _selectedState = MutableLiveData<Int?>()
    val selectedState: MutableLiveData<Int?>
        get() = _selectedState

    fun updateSelectedState(selected: Int?) {
        _selectedState.value = selected
    }
}