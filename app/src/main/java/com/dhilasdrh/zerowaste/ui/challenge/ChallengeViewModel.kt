package com.dhilasdrh.zerowaste.ui.challenge

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ChallengeViewModel : ViewModel(){
    private val _text = MutableLiveData<String>().apply {
        value = "This is challange Fragment"
    }
    val text: LiveData<String> = _text
}