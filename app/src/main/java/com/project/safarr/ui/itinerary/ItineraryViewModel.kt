package com.project.safarr.ui.itinerary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItineraryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Itinerary Fragment"
    }
    val text: LiveData<String> = _text
}