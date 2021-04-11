package com.dhilasdrh.zerowaste.repository

import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.model.User
import com.dhilasdrh.zerowaste.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference

class UserRepository {
    private val userRef = MyApplication.getFirestoreCollectionReferences(Constants.FS_USER)

    fun getUserRef(userId: String): DocumentReference {
        return userRef.document(userId)
    }

    fun setUserData(userId: String, user: User): Task<Void> {
        return userRef.document(userId).set(user)
    }
}