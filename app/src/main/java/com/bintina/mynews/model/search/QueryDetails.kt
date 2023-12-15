package com.bintina.mynews.model.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QueryDetails(
    val fieldName: String?,
    val filterName: String?,
    var checked: Boolean
): Parcelable