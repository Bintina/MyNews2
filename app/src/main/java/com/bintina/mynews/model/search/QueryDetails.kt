package com.bintina.mynews.model.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QueryDetails(
    var query: List<String?>,
    var startDate: String?,
    var endDate: String?,
    var checked: List<String?>?
): Parcelable