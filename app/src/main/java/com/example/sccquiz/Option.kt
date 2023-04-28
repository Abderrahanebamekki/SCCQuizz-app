package com.example.sccquiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Option(
    val text : String? = null
):Parcelable