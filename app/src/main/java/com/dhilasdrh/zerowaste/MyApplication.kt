package com.dhilasdrh.zerowaste

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dhilasdrh.zerowaste.utils.PreferencesManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class  MyApplication : Application() {
    companion object{
        private lateinit var application: MyApplication
        private lateinit var firebaseAuth: FirebaseAuth
        private lateinit var database: FirebaseDatabase
        private lateinit var preferencesManager: PreferencesManager

        fun getInstance() : MyApplication {
            return application
        }

        fun getFirebaseAuthInstance(): FirebaseAuth {
            return firebaseAuth
        }

        fun getFirestoreCollectionReferences(collection: String): CollectionReference {
            return FirebaseFirestore.getInstance().collection(collection)
        }

        fun getPreferencesManager(): PreferencesManager {
            return preferencesManager
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        preferencesManager = PreferencesManager.initPreferences()
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true)
    }

    fun getSharedPreferences(): SharedPreferences {
        return getSharedPreferences("user", Context.MODE_PRIVATE)
    }

}