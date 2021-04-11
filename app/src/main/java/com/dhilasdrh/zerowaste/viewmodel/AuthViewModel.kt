package com.dhilasdrh.zerowaste.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.model.User
import com.dhilasdrh.zerowaste.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    private val TAG = "AUTH_VIEW_MODEL"

    private val userRepository = UserRepository()
    val auth = MyApplication.getFirebaseAuthInstance()

    private val _userData = MutableLiveData<User>()
    fun getUserDataOnce(userId: String): LiveData<User> {
        userRepository.getUserRef(userId).get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(User::class.java) as User
            _userData.value = user
        }
        return _userData
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun loginUser(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun googleLoginUser(credential: AuthCredential): Task<AuthResult> {
        return auth.signInWithCredential(credential)
    }

    fun registerUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun createUser(user: User): Task<Void> {
        return userRepository.setUserData(user.userId!!, user)
    }

    fun logOutUser() {
        auth.signOut()
        MyApplication.getPreferencesManager().logOutUser()
    }
}