package com.example.koindependencyinjection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koindependencyinjection.repository.NumberRepository

class MainViewModel(private val numberRepository: NumberRepository) : ViewModel() {

    private val numberLiveData = MutableLiveData<Int>()

    fun getLiveData(): LiveData<Int> = numberLiveData

    fun generateNextNumber() {
        numberLiveData.postValue(numberRepository.generateNextNumber())
    }
}