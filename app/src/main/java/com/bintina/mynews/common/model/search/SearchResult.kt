package com.bintina.mynews.common.model.search


import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("response")
    val results: Result?,
    @SerializedName("status")
    val status: String?
)