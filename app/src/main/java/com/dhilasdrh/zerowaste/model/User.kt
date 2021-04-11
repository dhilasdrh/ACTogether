package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import com.dhilasdrh.zerowaste.utils.Commons
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var userId: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var photoUrl: String? = null,
    var role: String = "1",
    var createdAt: String = Commons.getCurrentDateTime(),
    var updatedAt: String = Commons.getCurrentDateTime()
): Parcelable {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userId,
            "name" to name,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "photoUrl" to photoUrl,
            "role" to role,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt
        )
    }
}