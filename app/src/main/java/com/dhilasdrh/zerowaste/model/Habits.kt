package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habits(
    var title: String,
    var description: String,
    var photo: String
): Parcelable
