package com.dhilasdrh.zerowaste.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel(){
    private val user = FirebaseAuth.getInstance().currentUser

    private val _displayName = MutableLiveData<String>().apply {
        value = user?.displayName
    }
    private val _email= MutableLiveData<String>().apply {
        value = user?.email
    }
    private val _photoUrl= MutableLiveData<Uri>().apply {
        value = user?.photoUrl
    }

    val displayName: LiveData<String> = _displayName
    val email: LiveData<String> = _email
    val photoUrl: LiveData<Uri> = _photoUrl
}