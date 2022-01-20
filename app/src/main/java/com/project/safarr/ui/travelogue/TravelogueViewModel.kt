package com.project.safarr.ui.travelogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TravelogueViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Travelogue Fragment"
    }
    val text: LiveData<String> = _text
}