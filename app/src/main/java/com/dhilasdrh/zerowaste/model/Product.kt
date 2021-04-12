package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var title: String,
    var totalSold: String,
    var price: String,
    var photo: String
) : Parcelable
