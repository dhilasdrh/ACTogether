package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post (
    var name: String? = null,
    var photoUrl: String? = null,
    var date: String? = null,
    var activity: String? = null,
    var activityIcon: String? = null,
    var caption: String? = null,
    var postImage: String? = null,
    var userId: String? = null,
) : Parcelable