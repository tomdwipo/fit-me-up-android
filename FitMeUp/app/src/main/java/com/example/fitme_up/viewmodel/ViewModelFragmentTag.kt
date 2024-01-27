package com.example.fitme_up.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelFragmentTag : ViewModel() {
    private val _fragmentTag = MutableLiveData<String?>()
    val selectedState: MutableLiveData<String?>
        get() = _fragmentTag

    fun updateSelectedState(selected: String?) {
        _fragmentTag.value = selected
    }
}