package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    var title: String,
    var time: String,
    var description: String,
    var photo: String
) : Parcelable