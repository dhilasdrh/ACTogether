package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DonateList(
    var title: String,
    var description: String,
    var img: String,
) : Parcelable