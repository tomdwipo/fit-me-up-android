package com.example.fitme_up.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelSelectDay : ViewModel() {
    private val _selectedDays = MutableLiveData<List<String>?>()
    val selectedDays: MutableLiveData<List<String>?>
        get() = _selectedDays

    fun addSelectedDay(day: String) {
        val updatedList = _selectedDays.value?.toMutableList()?.apply { add(day) }
        _selectedDays.value = updatedList ?: emptyList()
    }

    fun removeSelectedDay(day: String) {
        val updatedList = _selectedDays.value?.toMutableList()?.apply { remove(day) }
        _selectedDays.value = updatedList ?: emptyList()
    }

    fun clearSelectedDays() {
        selectedDays.value = mutableListOf()
    }

}