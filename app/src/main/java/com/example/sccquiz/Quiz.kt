package com.example.sccquiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Quiz(
    val nq: String? = null,
    val nbq: Int? = null,
    val tq: Int? = null,
    val rq: Float? = null,
    val img: String? = null,
    val listQuestions: List<Question?>?= null
 ): Parcelable