package com.example.sccquiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question (
    val question : String? = null,
    val listOption : List<Option?>? = null,
    val answer : String? = null
):Parcelable