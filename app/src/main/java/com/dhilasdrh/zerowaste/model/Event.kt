package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    var title: String,
    var description: String,
    var photo: String,
    var date: String,
    var time: String,
    var location: String
) : Parcelable