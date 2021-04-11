package com.dhilasdrh.zerowaste.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
        var title: String,
        var description: String,
        var photo: String
) : Parcelable